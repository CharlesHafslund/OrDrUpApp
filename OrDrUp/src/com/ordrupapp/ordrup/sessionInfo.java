package com.ordrupapp.ordrup;

import java.util.ArrayList;

import android.app.Application;


public enum sessionInfo{
	INSTANCE;
	private String username, passwordHash, sitecode;
	private ArrayList<table> tables;
	
	public boolean verify(String username, String password, String sitecode){
		
		passwordHash = password;  //need a function to conver this
	    //change this later to a API call
		if(true){
			setUsername(username);
			setPasswordHash(passwordHash);
			setSitecode(sitecode);
			return true;
		}
	    return false;
	}
	
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
	
	public ArrayList<table> getTables(){
		return tables;
	}
}
