package com.ordrupapp.ordrup;

import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
		vName.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
		TextView vPrice = new TextView(view.getContext());
		vPrice.setText(Double.toString(price));
		vPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
		EditText vNotes = new EditText(view.getContext());
		vNotes.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
		Button addItem = new Button(view.getContext());
		addItem.setText("Add");
		addItem.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		        Button myButton = (Button)v;
		        ///do something
		        
		               		        
		    }
		});
		addItem.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
		View[] itemView = {vName,vPrice,vNotes,addItem};
		return itemView;
		
	}
}
