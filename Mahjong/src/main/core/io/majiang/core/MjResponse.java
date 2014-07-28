package io.majiang.core;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * 
 * @author 志聪
 */
public class MjResponse {
	private short uri;
	private boolean isSuccess = true;
	private Map<Object,Object> result = new HashMap<Object,Object>();
	
	
	/**
	 * 发送错误信息
	 * @param erroMsg 错误详细信息
	 */
	public void sendErro(String erroMsg){
		isSuccess = false;
		result.put("state", "erro");
		result.put("error", erroMsg);
	}
	
	/**
	 * 将结果转变为json
	 * @return 返回结果的json字符串
	 */
	public String toJson(){
		Gson gson = new Gson();
		if(isSuccess){
			result.put("status", "success");
		}
		return gson.toJson(result);
	}
	
	
	/**
	 * 
	 * @param key 
	 * @param value
	 */
	public void add(Object key,Object value){
		result.put(key, value);
	}
	
	/**
	 * 
	 * @param key
	 */
	public void remove(Object key){
		result.remove(key);
	}
	
	/**
	 * 
	 * @return
	 */
	public short getUri() {
		return uri;
	}
	
	/**
	 * 
	 * @param uri
	 */
	public void setUri(short uri) {
		this.uri = uri;
	}
}
