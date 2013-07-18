package com.ordrupapp.ordrup;

public class menuItem {
	private int menuItemID;
	private String name;
	private double price;
	
	menuItem(int menuItemID, String name, double price){
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
	
	public double getPrice(){
		return price;
	}
}
