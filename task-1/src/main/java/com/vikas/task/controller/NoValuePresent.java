package com.vikas.task.controller;

import org.springframework.stereotype.Component;

@Component
public class NoValuePresent extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private int code;
	private String message;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public NoValuePresent(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public NoValuePresent() {
		super();
	}
	

}
