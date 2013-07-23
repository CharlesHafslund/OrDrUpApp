package com.ordrupapp.ordrup;

public class orderItem {
	private String name,notes;
	private float price;
	int menuItemID, orderItemID;//, orderID;
	
	//is orderID needed here?
	//no can be slipped inline when the order is submitted
	
	orderItem(int menuItemID){
		this.menuItemID = menuItemID;
		//handle the rest
	}
	
	//constructor without orderitem notes
	orderItem(int menuItemID, String name, float price){
		this(menuItemID, name, price, "");
	}
	
	
	//constructor with notes
	orderItem(int menuItemID, String name, float price, String notes){
		this.menuItemID = menuItemID;
		//this.orderID = orderID;
		this.name = name.toString();
		this.price = price;
		this.notes = notes.toString();
	}
	
	public int getMenuItemID(){
		return menuItemID;
	}
	
//	public int getOrderID(){
//		return orderID;
//	}
	
	public String getName(){
		return name.toString();
	}
	
	public float getPrice(){
		return price;
	}
	
	public String getNotes(){
		return notes.toString();
	}
	
	
	
	
}
