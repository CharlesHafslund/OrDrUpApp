package com.ordrupapp.ordrup;

import java.util.ArrayList;

public class order {
	private int orderID, tableOrderID;
	private ArrayList<orderItem> orderItems;
	private int status;
	
	order(int tableOrderID){
		//set the table order id
		this.tableOrderID = tableOrderID;
	}
	
	public void addOrderItem(int menuItemID){
		orderItems.add(new orderItem(menuItemID));
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
