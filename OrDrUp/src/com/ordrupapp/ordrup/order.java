package com.ordrupapp.ordrup;

import java.util.ArrayList;

public class order {
	//private int orderID, tableOrderID;
	private ArrayList<orderItem> orderItems;
	private int status;
	
	order(){
		//set the table order id
		//this.tableOrderID = tableOrderID;
		orderItems = new ArrayList<orderItem>();
	}
	
	public void addOrderItem(int menuItemID){
		orderItems.add(new orderItem(menuItemID));
	}
	
	public void addOrderItem(int category, int menuItemIndex){
		orderItems.add(new orderItem(menu.INSTANCE.getMenuItemList(category).get(menuItemIndex).getMenuItemID(), 
									 menu.INSTANCE.getMenuItemList(category).get(menuItemIndex).getName(), 
									 menu.INSTANCE.getMenuItemList(category).get(menuItemIndex).getPrice()));
		//orderItem(int menuItemID, String name, float price)
	}
	
	
	//remove?
	public void addOrderItem(int menuItemID, String name, float price){
		addOrderItem(menuItemID, name, price, "");
	}
	
	
	//remove?
	public void addOrderItem(int menuItemID, String name, float price, String notes){
		orderItems.add(new orderItem(menuItemID, name, price, notes));
	}
	
	public int getOrderItemCount(){
		return orderItems.size();
	}
	
	public ArrayList<orderItem> getOrderItems(){
		return orderItems;
	}
	
	public int getStatus(){
		//get the order status from the DB and return it
		return 0;
	}
	
	public void submitOrder(){
		//create new order and get back order ID
		//store order ID locally
		//bundle orderitems and submit
		
	}
}
