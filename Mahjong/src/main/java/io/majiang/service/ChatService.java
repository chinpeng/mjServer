package io.majiang.service;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import io.majiang.annotation.GameService;
import io.majiang.core.MjRequest;
import io.majiang.core.MjResponse;
import io.majiang.entity.User;
import io.majiang.exception.GameServceException;
import io.majiang.session.Room;
import io.majiang.session.RoomManager;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelMatchers;


@GameService(Route = "chat")
public class ChatService implements BaseService{

	public void invoke(MjRequest request, MjResponse response)
			throws GameServceException {
		CacheManager manager = CacheManager.create();
		String sid = (String) request.getAttribute("sid");
		Cache userCache= manager.getCache("user-"+sid);
		
		//检测当前状态,是否已进入房间
		int status = (Integer) userCache.get("status").getObjectValue();
		if (status != User.OUTSIDE_THE_ROOM) {
			response.sendErro("inside the room");
			return ;
		}
		
		String roomid = (String) userCache.get("roomNum").getObjectValue();
		

		RoomManager roomManager = new RoomManager();
		Room room = roomManager.get(roomid);
		ChannelGroup cg =  room.getChannels();
		
		Map<String,String> responseMap = new HashMap<String,String>();
		responseMap.put("msg", "你逗我");
		responseMap.put("type","text");
		/**
		 * 几号位
		 */
		Gson gson = new Gson();
		cg.writeAndFlush(gson.toJson(responseMap)+"\r\n",  ChannelMatchers.isNot(request.getChannel()));
		
	}
	
}
