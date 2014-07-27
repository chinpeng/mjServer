package io.majiang.core;

import io.netty.channel.Channel;

public class UserChannel {
	private long lastTime;
	private Channel channel;
	

	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public long getLastTime() {
		return lastTime;
	}
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
}
