package com.ordrupapp.ordrup;

import java.util.ArrayList;

public class table implements Comparable {
	int tableID, 		//this is the tableID in the database
		tableNumber;	//this is the restaurant's simple table number
	ArrayList<order> tableOrders;
	
	table(int tableID, int tableNumber){
		this.tableID = tableID;
		this.tableNumber = tableNumber;
		tableOrders = new ArrayList<order>(1);
	}
	
	public int addOrder(){
		tableOrders.add(new order());
		System.out.println("-------- table order size is now " + tableOrders.size());
		return tableOrders.size() - 1; //return the order number index
	}
	
	public int getTableID(){
		return tableID;
	}
	
	public int getTableNumber(){
		return tableNumber;
	}
	
	public ArrayList<order> getOrders(){
		return tableOrders;
	}
	
//	public void addOrderItem(int orderIndex, int menuItemID){
//		int orderIndex = orderNumber-1;
//		if (orderIndex < tableOrders.size()){
//			tableOrders.get(orderIndex).addOrderItem(menuItemID);
//		}
//	}
	
	public void addOrderItem(int orderIndex, int sectionID, int menuItemID){

			tableOrders.get(orderIndex).addOrderItem(sectionID,menuItemID);	
	}
	
	public int getOrderCount(){
		if (tableOrders.isEmpty()) return 0;
		
		else return tableOrders.size();
	}
	
	public boolean clearTable(){
		//check it bill paid for table
		//if paid, set status to open
		return true;
	}

	@Override
	//to sort tables for display
	public int compareTo(Object anotherTable) {
		if (!(anotherTable instanceof table))
		      throw new ClassCastException("A table object expected.");
		    int anotherTableNumber = ((table) anotherTable).getTableNumber();  
		    return this.getTableNumber() - anotherTableNumber;  
	}
	
	  @Override
	  public boolean equals(Object v) {
		  if (this.getTableNumber()  == ((table) v).getTableNumber()) return true;
		  else return false;
	  }
}
