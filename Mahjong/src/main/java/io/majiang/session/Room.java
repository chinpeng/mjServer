package io.majiang.session;



import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;


public class Room {
	/**
	 * 房间最大数量
	 */
	public static final int ROOM_MAX_SIZE = 4;
	
	private int roomid;
	 private ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	 
	 public int size() {
		return channels.size();
	}
	 public boolean add(Channel c){
		 if(channels.size()>=ROOM_MAX_SIZE)
			 return false;
		 channels.add(c);	
		 return true;
	 }
	 public void remove(Channel c){
		 channels.remove(c);
	 }
	public int getRoomid() {
		return roomid;
	}
	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}
	public ChannelGroup getChannels() {
		return channels;
	}
	public void setChannels(ChannelGroup channels) {
		this.channels = channels;
	}
	 

}
