package io.majiang.service;



import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;



import io.majiang.annotation.Filter;
import io.majiang.annotation.GameService;
import io.majiang.core.MjRequest;
import io.majiang.core.MjResponse;
import io.majiang.entity.User;
import io.majiang.exception.GameServceException;
import io.majiang.session.Room;
import io.majiang.session.RoomManager;


@GameService(Route = "enter")
@Filter(filterName = "isLogin")
public class EnterRoomService implements BaseService{
	
	public void invoke(MjRequest request, MjResponse response) throws GameServceException{
		CacheManager manager = CacheManager.create();
		
		String sid = (String) request.getAttribute("sid");
		String roomid = (String) request.getAttribute("roomid");
		
		//检测当前状态,是否已进入房间
		Cache userCache= manager.getCache("user-"+sid);
		int status = (Integer) userCache.get("status").getObjectValue();
		if (status != User.OUTSIDE_THE_ROOM) {
			response.sendErro("inside the room");
			return ;
		}	
		
		RoomManager roomManager = new RoomManager();
		Room room = roomManager.get(roomid);
		int roomSize = room.getChannels().size();
		
		//房间是否满人
		if(roomSize >= Room.ROOM_MAX_SIZE){
			response.sendErro("full");
			return ;
		}
		
		room.add(request.getChannel());
		Cache user = manager.getCache("user-"+sid);
		Element e1 = new Element("status", User.IN_THE_ROOM);
		Element e2 = new Element("roomNum", roomid);
		user.put(e1);
		user.put(e2);

	}
	
}
