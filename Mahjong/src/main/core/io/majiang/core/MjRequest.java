package io.majiang.core;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import io.majiang.session.StandardSession;
import io.netty.channel.Channel;

public class MjRequest {
	private String Uri;
	private Map<Object,Object> attribute;
	private Channel channel;
	private StandardSession session;
	public MjRequest(String uri, String json, Channel channel){
		this.Uri = uri;
		this.channel = channel;
		Gson gson = new Gson();
		attribute = (Map<Object, Object>) gson.fromJson(json, Map.class);
	}
	public Object getAttribute(Object key){
		return attribute.get(key);
	}
	public String getUri() {
		return Uri;
	}
	public void setUri(String uri) {
		Uri = uri;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}
