package com.ordrupapp.ordrup;

import android.app.Application;


public class sessionInfo extends Application {
	private String username, passwordHash, sitecode;
	
	public void setUsername(String username){
		this.username = username.toString();
	}
	
	public void setPasswordHash(String passwordHash){
		this.passwordHash = passwordHash.toString();
	}
	
	public void setSitecode(String sitecode){
		this.sitecode = sitecode.toString();
	}
	
	public String getUsername(){
		return username.toString();
	}
	
	public String getPasswordHash(){
		return passwordHash.toString();
	}
	
	public String getSitecode(){
		return sitecode.toString();
	}
}
