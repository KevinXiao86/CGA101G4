package com.taiwan.beans;

import java.io.Serializable;

public class Core implements Serializable {
	private static final long serialVersionUID = 1457755989409740329L;
	private boolean successful;//是否成功
	private String message;//設置錯誤訊息
	private String url;//設置地址值
	
	public Core() {
		super();
	}
	public Core(boolean successful, String message) {
		super();
		this.successful = successful;
		this.message = message;
	}
	public Core(boolean successful, String message, String url) {
		super();
		this.successful = successful;
		this.message = message;
		this.url = url;
	}
	
	public boolean isSuccessful() {
		return successful;
	}
	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Core [successful=" + successful + ", message=" + message + ", url=" + url + "]";
	}
}
