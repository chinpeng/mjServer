package io.majiang.session;

import io.netty.channel.Channel;



public class StandardSession implements Session{
	protected long lastUsedTime;
	protected Channel channel;
	protected SessionManager sessionManager;
	
	   /**
	    * The session identifier of this Session.
	   */
	protected String id = null;
	    /**
	     * Return the session identifier for this session.
	     */
	    public String getId() {

	        return (this.id);

	    }


	    /**
	     * Return the session identifier for this session.
	     */
	    public String getIdInternal() {
	        return (this.id);
	    }
	    
	public void expire(){
		sessionManager.remove(this);
	}
	public long getLastUsedTime() {
		return lastUsedTime;
	}
	public void setLastUsedTime(long lastUsedTime) {
		this.lastUsedTime = lastUsedTime;
	}

}
