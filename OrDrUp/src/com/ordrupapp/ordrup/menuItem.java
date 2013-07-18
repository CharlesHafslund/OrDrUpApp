package com.ordrupapp.ordrup;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
	public View[] getViewArray(View view){
		TextView vName = new TextView(view.getContext());
		vName.setText(name);
		TextView vPrice = new TextView(view.getContext());
		vPrice.setText(Double.toString(price));
		EditText vNotes = new EditText(view.getContext());
		Button addItem = new Button(view.getContext());
		View[] itemView = {vName,vPrice,vNotes,addItem};
		return itemView;
		
	}
}
