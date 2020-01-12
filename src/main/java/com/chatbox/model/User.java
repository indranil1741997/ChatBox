package com.chatbox.model;

public class User {
	private String email;
	private String name;
	private String phoneNum;
	private String password;
	
	public User(String email, String name, String phoneNum, String password) {
		super();
		this.email = email;
		this.name = name;
		this.phoneNum = phoneNum;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
}
