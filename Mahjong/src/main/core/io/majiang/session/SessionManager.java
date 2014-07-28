package io.majiang.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class SessionManager implements Runnable{
	private final long CHECK_TIME = 60000L;   //60s过期检查一次
	private final long maxInactiveInterval = 600L;  //session有效时间60s
	private boolean threadDone = false;
	
	protected static Map<String, Session> sessions = new ConcurrentHashMap<String, Session>();
	
	public void run() {
	         while (!threadDone) {
	                 try {
	                	 Thread.sleep(CHECK_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	                 processExpires();
	             }
	}
	private void processExpires() {
	         long timeNow = System.currentTimeMillis();
	         synchronized(sessions){
		         Session[] sessions = findSessions();
		         for (int i = 0; i < sessions.length; i++) {
		             StandardSession session = (StandardSession) sessions[i];
		             long timeIdle = // Truncate, do not round up
		               ((timeNow - session.getLastUsedTime()) / 1000L);
		             if (timeIdle >= maxInactiveInterval) {
		        	     session.expire();
		             }	
		         }
	         }
	}
	public void add(Session session){
		sessions.put(session.getIdInternal(), session);
	}
	public void remove(Session session){
		sessions.remove(session.getIdInternal());
	}
	private Session[] findSessions() {
		return sessions.values().toArray(new Session[0]);
	}

}
