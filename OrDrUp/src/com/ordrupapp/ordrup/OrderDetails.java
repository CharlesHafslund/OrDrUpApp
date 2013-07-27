package com.ordrupapp.ordrup;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class OrderDetails extends Activity {
	
	
	LinearLayout layout;
	int orderNumber, currentTableIndex;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_details);
		// Show the Up button in the action bar.
		setupActionBar();
		Bundle extras = getIntent().getExtras();
		if (extras != null) {

			//TextView tableNumberText = (TextView) findViewById(R.id.table_details_number);
			//String table = extras.getString("tableNumber");
			currentTableIndex = extras.getInt("tableIndex");
			orderNumber = extras.getInt("orderNumber");
			System.out.println("TID = " + currentTableIndex);
			System.out.println("ON = " + orderNumber);
			//tableNumberText.setText(table);
			
	

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
		
		if (null != layout && layout.getChildCount() > 0) {                 
			try {
				layout.removeViews (0, layout.getChildCount());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		layout = (LinearLayout) findViewById(R.id.order_details_list);
		
		TextView orderItemName;
		
		System.out.println("TID = " + currentTableIndex);
		System.out.println("ON = " + orderNumber);
		for (int i = 0; i < mySession.getTables().get(currentTableIndex).getOrders().get(orderNumber).getOrderItemCount(); i++){
			System.out.println("Trying to add an order item");
			orderItemName = new TextView(view.getContext());
			orderItemName.setText ("hello");
			mySession.getTables()
											.get(currentTableIndex)
											.getOrders()
											.get(orderNumber)
											.getOrderItems()
											.get(i).getName();
			//layout.addView(orderItemName);
		}
		
	}

}
