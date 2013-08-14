package com.ordrupapp.ordrup;

/*
 * This is an object to hold an instance of a menuItem that has
 * been added to a specific order
 */
public class orderItem {
	private String name,notes;
	private double price;
	int menuItemID, orderItemID = -1;

	
	//constructor without orderitem notes
	orderItem(int menuItemID, String name, double price){
		
		this(menuItemID, name, price, "");
	}
	
	
	//constructor with notes
	orderItem(int menuItemID, String name, double price, String notes){
		
		this.menuItemID = menuItemID;
		this.name = name.toString();
		this.price = price;
		this.notes = notes.toString().replaceAll(" ", "_"); //spaces are invalid in the API request string
		
	}
	
	public int getMenuItemID(){
		return menuItemID;
	}
	
	public void setOrderItemID(int orderItemID){
		this.orderItemID = orderItemID;
	}
	
	public int getOrderItemID(){
		return orderItemID;
	}
	
	public String getName(){
		return name.toString();
	}
	
	public double getPrice(){
		return price;
	}
	
	public String getStatus(){
		return APIRequestor.getOrderItemStatus(orderItemID);
	}
	
	public String getNotes(){
		return notes.toString();
	}
	
	
	
	
}
