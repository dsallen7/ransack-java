package com.allen.silo.ransack.handlers;

import org.mini2Dx.minibus.MessageData;

public class LogMessageDataImpl implements MessageData {

	private final int value;
	private String message;
	
	public LogMessageDataImpl(int value, String message){
		this.value = value;
		this.setMessage(message);
	}

	public int getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
