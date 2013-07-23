package com.ordrupapp.ordrup;

import java.util.ArrayList;

import android.app.Application;

public enum menu{
	INSTANCE;
	public final int 	BEVERAGES = 0,
						APPETIZERS = 1,
						MAIN_COURSE = 2,
						DESSERT = 3;
	
	//private int timestamp;  //possibly use for timestamping the menu
	
	private ArrayList<menuItem> beverages = new ArrayList<menuItem>(), 
								appetizers = new ArrayList<menuItem>(),
								mainCourse = new ArrayList<menuItem>(),
								dessert = new ArrayList<menuItem>();
			
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
		//this will eventually be an API call to get the items and add them in
		
		beverages.clear();
		beverages.add(new menuItem(111, "Pop", 2.99));
		beverages.add(new menuItem(112, "Beer", 4.99));
		beverages.add(new menuItem(113, "Wine - LeCheapo DeLaBoxo (Glass)", 3.99));
		beverages.add(new menuItem(114, "Tall Glass STFU", 0.00));
		
		appetizers.clear();
		appetizers.add(new menuItem(114, "Lamb Fries", 6.99));
		appetizers.add(new menuItem(115, "Surprise Item", 5.99));
		appetizers.add(new menuItem(116, "Leftover Mishmash", 4.99));
		
		mainCourse.clear();
		mainCourse.add(new menuItem(117, "Hamburger", 6.99));
		mainCourse.add(new menuItem(118, "Chicken Fingers", 6.99));
		mainCourse.add(new menuItem(119, "Lutefisk Hotdish", 6.99));
		mainCourse.add(new menuItem(120, "Block o' Spam", 6.99));
		
		dessert.clear();
		dessert.add(new menuItem(121, "Super Brownies", 6.99));
		dessert.add(new menuItem(122, "Not So Super Brownies", 5.99));
		dessert.add(new menuItem(123, "Just A Brownie", 4.99));
		dessert.add(new menuItem(124, "Bag of Brownie Mix", 3.99));
		
	}

}
