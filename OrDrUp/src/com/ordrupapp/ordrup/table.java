package com.ordrupapp.ordrup;

import java.util.ArrayList;

public class table {
	int tableID, 		//this is the tableID in the database
		tableNumber;	//this is the restaurant's simple table number
	ArrayList<order> tableOrders;
	
	table(int tableID, int tableNumber){
		this.tableID = tableID;
		this.tableNumber = tableNumber;
		tableOrders = new ArrayList<order>(0);
	}
	
	public void addOrder(){
		tableOrders.add(new order(tableOrders.size()+1));
	}
	
	public int getTableID(){
		return tableID;
	}
	
	public int getTableNumber(){
		return tableNumber;
	}
	
}
