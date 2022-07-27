package com.ECommerence.entity;

public class users {
	
	private String signal = "users" ; 
	private int user_id ;  
	private String email ; 
	private String passwordString ;
	private String full_name ;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	private  String user_name = "" ;
	public int getUser_id() {
		return user_id;
	} 
	
	public String getSignal() {
		return signal;
	}

	public void setSignal(String signal) {
		this.signal = signal;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordString() {
		return passwordString;
	}
	public void setPasswordString(String passwordString) {
		this.passwordString = passwordString;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public users() {
		this.user_name = "" ;
	}
}
