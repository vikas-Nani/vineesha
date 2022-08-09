package com.vikas.projects.Wicket_application_spring_2;

import java.io.Serializable;

public class Student  implements Serializable {
	
	private String id;
	private String name;
	private String email;
	private String mobilenumber;
	private String hometown;
	
	
	
	public Student() {
		super();
	}
	public Student(String id, String name, String email,String mobilenumber,String hometown) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobilenumber=mobilenumber;
		this.hometown=hometown;
	}
	
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
