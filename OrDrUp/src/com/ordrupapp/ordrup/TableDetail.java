package com.ordrupapp.ordrup;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class TableDetail extends Activity {
	LinearLayout layout;
	//need to make a call here to get orders for table
	String orders[] = {"1","2"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table_detail);
		// Show the Up button in the action bar.
		setupActionBar();
		
		//set the table number for the screen
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		
		    TextView tableNumberText = (TextView) findViewById(R.id.table_details_number);
		    String table = extras.getString("table");
		    tableNumberText.setText(table);
		    getOrders((View)findViewById(R.id.orderButtons_list));
	
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
		getMenuInflater().inflate(R.menu.table_detail, menu);
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
	
	//dummy method, add real functionality
public void addOrder(View view){
	String[] newOrders = new String[orders.length +1];
	for (int i = 0; i < orders.length; i++){
		newOrders[i] = orders[i];
	}
	newOrders[orders.length] = "X";
	orders = newOrders;
	
	getOrders((View)findViewById(R.id.orderButtons_list));
	Intent intent = new Intent(view.getContext(), MenuScreen.class);
    intent.putExtra("order", "X");
    startActivity(intent);
}
	
public void getOrders(View view) {
				
		
	int orderCount = orders.length;
		
		sessionInfo mySession = sessionInfo.INSTANCE; //((sessionInfo)this.getApplication());
		//debug message
    	Toast.makeText(getApplicationContext(), "SiteCode: " + mySession.getSitecode() + "\nUsername: " + mySession.getUsername() + "\nPassword: " + mySession.getPasswordHash(), Toast.LENGTH_LONG).show();
    	
		Button btn[] = new Button[orderCount];
		
		if (null != layout && layout.getChildCount() > 0) {                 
			try {
				layout.removeViews (0, layout.getChildCount());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		//update this for orders versus tables
		//Handler for dynamic buttons, passes in the table number
		View.OnClickListener btnHandler = new View.OnClickListener() {
		    public void onClick(View v) {
		        Button orderButton = (Button)v;
		        String buttonText = orderButton.getText().toString();
		        //Intent intent = new Intent(v.getContext(), TableDetail.class);
		        //intent.putExtra("table", buttonText);
		        //startActivity(intent);
		    }
		};
		
		
		layout = (LinearLayout) findViewById(R.id.orderButtons_list);
		
		//create the buttons based on the table list and assign the btnHandler to each
		for (int i=0;i<orderCount;i++){
			btn[i] = new Button(this);
			btn[i].setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			btn[i].setBackgroundResource(R.drawable.large_plate);  // add image for kicks
			btn[i].setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
			btn[i].setText("Order " + orders[i]);
			btn[i].setOnClickListener(btnHandler);

			layout.addView(btn[i]);
		}
		
	}
	

}
