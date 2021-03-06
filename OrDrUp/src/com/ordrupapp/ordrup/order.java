package com.ordrupapp.ordrup;

import java.util.ArrayList;

import android.widget.Toast;

public class order {
	private int tableID, orderID;
	private ArrayList<orderItem> orderItems;
	private boolean submitted;

	order(int tableID){
		//set the table order id
		this.tableID = tableID;
		orderItems = new ArrayList<orderItem>();
		submitted = false;
	}



	public void addOrderItem(int category, int menuItemIndex){
		orderItems.add(new orderItem(menu.INSTANCE.getMenuItemList(category).get(menuItemIndex).getMenuItemID(), 
				menu.INSTANCE.getMenuItemList(category).get(menuItemIndex).getName(), 
				menu.INSTANCE.getMenuItemList(category).get(menuItemIndex).getPrice()));
		
	}

	public void addOrderItem(int category, int menuItemIndex, String notes){
		orderItems.add(new orderItem(menu.INSTANCE.getMenuItemList(category).get(menuItemIndex).getMenuItemID(), 
				menu.INSTANCE.getMenuItemList(category).get(menuItemIndex).getName(), 
				menu.INSTANCE.getMenuItemList(category).get(menuItemIndex).getPrice(),
				notes));
	
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

	public boolean wasSubmitted(){
		//get the order status from the DB and return it
		return submitted;
	}


	public boolean submitOrder(){
		
		orderID = APIRequestor.addOrderToTable(tableID);		
		int orderItemID = -1;
		

		if (orderID >= 0 ){
			for (int i = 0; i < orderItems.size(); i++){
				
				orderItemID = APIRequestor.addOrderItemToOrder(orderID, orderItems.get(i).getMenuItemID(), orderItems.get(i).getPrice(), orderItems.get(i).getNotes());
				if (orderItemID >= 0){
					
					orderItems.get(i).setOrderItemID(orderItemID);
				}
				else {
					//we should delete the order from the system
				}
			}
			submitted = true;
		}
		
		return submitted;

	}


}
