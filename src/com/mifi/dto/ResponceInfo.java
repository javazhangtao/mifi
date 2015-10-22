package com.mifi.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResponceInfo {

	Integer code;//返回状态码
	String message;//返回说明
	Object data;//返回数据对象
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		Gson gson=new GsonBuilder()
			    .disableHtmlEscaping()
			    .serializeNulls()
			    .create();;
		return gson.toJson(this);
	}
	
	
}
