package com.ordrupapp.ordrup;

public class menuItem {
	private int menuItemID;
	private String name;
	private float price;
	
	menuItem(int menuItemID, String name, float price){
		this.menuItemID = menuItemID;
		this.name = name.toString();
		this.price = price;
		
	}
	
	public int getMenuItemID(){
		return menuItemID;
	}
	
	public String getName(){
		return name.toString();
	}
	
	public float getPrice(){
		return price;
	}
}
