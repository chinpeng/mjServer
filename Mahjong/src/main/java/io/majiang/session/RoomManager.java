package io.majiang.session;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.ehcache.CacheOperationOutcomes.GetAllOutcome;

public class RoomManager {
	private static ConcurrentHashMap<String,Room>  roomMap;
	static {
		roomMap = new ConcurrentHashMap<String,Room>();
		for(int i = 0; i < 50;i++ ){
			Room r= new Room();
			roomMap.put(Integer.toString(i), r);
		}
	}
	
	public  Room get(String roomid){
		return roomMap.get(roomid);
	}
	
}
