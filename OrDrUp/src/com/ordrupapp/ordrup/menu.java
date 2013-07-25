package com.ordrupapp.ordrup;

import java.util.ArrayList;

import com.google.gson.Gson;

import android.app.Application;
import android.os.StrictMode;
import android.widget.Toast;

public enum menu{
	INSTANCE;
	public final int 	BEVERAGES = 0,
						APPETIZERS = 1,
						MAIN_COURSE = 2,
						DESSERT = 3;
	
	private ArrayList<menuItem> beverages = new ArrayList<menuItem>(), 
								appetizers = new ArrayList<menuItem>(),
								mainCourse = new ArrayList<menuItem>(),
								dessert = new ArrayList<menuItem>();
	private String debugST;
			
	public ArrayList<menuItem> getMenuItemList(int type){
		
		switch(type) {
		case 0:
			return beverages;
		case 1:
			return appetizers;
		case 2:
			return mainCourse;
		case 3:
			return dessert;
		default:
			break;
		}
		return null;

	}
	
	public void updateMenu(){
				
		beverages.clear();
		appetizers.clear();
		mainCourse.clear();
		dessert.clear();
		String jsonReq = APIRequestor.get("menuItem", "");
		
		//this is null for some reason
		debugST = jsonReq;
		//APIRequestor.jsonToMenuItemArray(jsonReq);
//		ArrayList<ArrayList<menuItem>> myMenu = 
//				(ArrayList<ArrayList<menuItem>>) APIRequestor.jsonToMenuItemArray(jsonReq).clone();
//		beverages = (ArrayList<menuItem>) myMenu.get(BEVERAGES).clone();
//		appetizers = (ArrayList<menuItem>) myMenu.get(APPETIZERS).clone();
//		mainCourse = (ArrayList<menuItem>) myMenu.get(MAIN_COURSE).clone();
//		dessert = (ArrayList<menuItem>) myMenu.get(DESSERT).clone();
		
	}

	public String getDebugST() {
		return debugST;
	}


}
