package com.ordrupapp.ordrup;

import java.util.ArrayList;

import android.app.Application;


public enum sessionInfo{
	INSTANCE;

	//testing this out
	public static sessionInfo getInstance(){
	
		
		return INSTANCE;
	}
	

	
private String username, password, sitecode;
	private ArrayList<table> tables;

	public boolean verify(String username, String password, String sitecode){

		
		//change this later to a API call
		if(true){
			setUsername(username);
			setPassword(password);
			setSitecode(sitecode);
			tables = new ArrayList<table>();
			return true;
		}
		return false;
	}

	public void setUsername(String username){
		this.username = username.toString();
	}

	public void setPassword(String password){
		this.password = password.toString();
	}

	public void setSitecode(String sitecode){
		this.sitecode = sitecode.toString();
	}
	
	public void clear(){
		this.username = null;
		this.password = null;
	}

	public String getUsername(){
		return username.toString();
	}

	public String getPassword(){
		return password.toString();
	}

	public String getSitecode(){
		return sitecode.toString();
	}

	public ArrayList<table> getTables(){
		return tables;
	}

	public boolean findTableByNumber(int tableNumber){

		if(tables.size() > 0){

			for (int i = 0; i < tables.size(); i++){

				if (tables.get(i).getTableNumber() == tableNumber) return true;

			}
		}

		return false;
	}

	public boolean findTableByID(int tableID){

		if(tables.size() > 0){

			for (int i = 0; i < tables.size(); i++){

				if (tables.get(i).getTableID() == tableID) return true;

			}
		}

		return false;
	}

}
