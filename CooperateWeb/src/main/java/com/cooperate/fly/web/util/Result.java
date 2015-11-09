package com.cooperate.fly.web.util;

public class Result {

	private boolean successful=true;
	private String message;
	private Object data;
	
	public Result(){
	}
	
	public Result(boolean successful){
		this(successful,null);
	}
	
	public Result(boolean successful,String msg){
		this.successful=successful;
		this.message=msg;
	}
	
	public Result(String msg){
		this(false,msg);
	}
	
	public boolean isSuccessful(){
		return successful;
	}
	
	public void setSuccessful(boolean success){
		this.successful=success;
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
}
