package com.ordrupapp.ordrup;

import java.util.ArrayList;

public class table {
	int tableID, tableNumber;
	ArrayList<order> tableOrders;
	
	table(int tableID, int tableNumer){
		this.tableID = tableID;
		this.tableNumber = tableNumber;
		tableOrders = new ArrayList<order>(0);
	}
	
	public void addOrder(){
		tableOrders.add(new order(tableOrders.size()+1));
	}
	
}
