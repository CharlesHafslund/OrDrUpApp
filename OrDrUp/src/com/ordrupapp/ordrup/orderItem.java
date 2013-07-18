package com.ordrupapp.ordrup;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class orderItem {
	private String name,notes;
	private float price;
	int menuItemID, orderItemID, orderID;
	
	//is orderID needed here?
	//no can be slipped inline when the order is submitted
	
	//constructor without orderitem notes
	orderItem(int menuItemID, int orderID, String name, float price){
		this(menuItemID, orderID, name, price, "");
	}
	
	
	//constructor with notes
	orderItem(int menuItemID, int orderID, String name, float price, String notes){
		this.menuItemID = menuItemID;
		this.orderID = orderID;
		this.name = name.toString();
		this.price = price;
		this.notes = notes.toString();
	}
	
	public int getMenuItemID(){
		return menuItemID;
	}
	
	public int getOrderID(){
		return orderID;
	}
	
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
