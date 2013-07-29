package com.ordrupapp.ordrup;

import android.os.Bundle;
import android.app.Activity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class OrderDetails extends Activity {
	
	
	GridLayout layout;
	int orderNumber, currentTableIndex;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_details);
		// Show the Up button in the action bar.
		setupActionBar();
		Bundle extras = getIntent().getExtras();
		if (extras != null) {

			currentTableIndex = extras.getInt("tableIndex");
			orderNumber = extras.getInt("orderNumber");
			
			getOrderItems((View)findViewById(R.id.order_details_list));
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void getOrderItems(View view){
		sessionInfo mySession = sessionInfo.getInstance();
		
		GridLayout layout = (GridLayout) view.findViewById(R.id.order_details_list);
		
		//clear the view
		layout.removeAllViews();
		
		layout.setColumnCount(2);
		
		//declare the fields
		TextView orderItemName;
		Button removeItem;
		TextView orderItemNotes;
		String orderItemNotesString;
		
		for (int i = 0; i < mySession.getTables().get(currentTableIndex).getOrders().get(orderNumber).getOrderItemCount(); i++){
						
			//add the remove button
			removeItem = new Button(view.getContext());
			removeItem.setText("-");
			removeItem.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
			layout.addView(removeItem);
			
			//get the name
			orderItemName = new TextView(view.getContext());
			orderItemName.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
			orderItemName.setText (
						mySession.getTables()
											.get(currentTableIndex)
											.getOrders()
											.get(orderNumber)
											.getOrderItems()
											.get(i).getName());
			layout.addView(orderItemName);
			
			//get the notes
			orderItemNotesString = mySession.getTables()
					.get(currentTableIndex)
					.getOrders()
					.get(orderNumber)
					.getOrderItems()
					.get(i)
					.getNotes();
			
			orderItemNotes = new TextView(view.getContext());
			orderItemNotes.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
			
			//add a dash if there are notes
			if (!orderItemNotesString.isEmpty()) orderItemNotes.setText(" - " + orderItemNotesString);
			
			//else add the empty string
			else orderItemNotes.setText(orderItemNotesString);
			
			
		}
		
	}

}
