package com.wsy;

public class user {
	String name;
	String password;
	public user(){
		name="";
		password="";
	}
	public String getName(){
		return this.name;
	}
	public String getPassword(){
		return this.password;
	}
	public void setName(String name){
		this.name=name;
		
	}
	public void setPassword(String password){
		this.password=password;
	}
	
}
