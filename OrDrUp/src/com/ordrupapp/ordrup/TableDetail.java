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
	//String orders[] = {"1","2"};
	int currentTableIndex;
	//ArrayList<order> myOrders;
	
    private static String[] tag = new String[5];
	
	private final int TABLE_INDEX = 0,
						ORDER_NUMBER = 1,
						MENU_TYPE = 2,
						MENU_INDEX = 3,
						NOTES = 4;

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
			String table = extras.getString("tableNumber");
			currentTableIndex = extras.getInt("tableIndex");
			tag[TABLE_INDEX] = Integer.toString(currentTableIndex);
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

		sessionInfo mySession = sessionInfo.getInstance();
		int orderNumber = mySession.getTables().get(currentTableIndex).addOrder();
			
			getOrders((View)findViewById(R.id.orderButtons_list));
			Intent intent = new Intent(view.getContext(), MenuScreen.class);
		    intent.putExtra("orderNumber", orderNumber);
		    intent.putExtra("table", currentTableIndex);
			    startActivity(intent);
	}

	public void getOrders(View view) {
		sessionInfo mySession = sessionInfo.getInstance();
			
		int orderCount = mySession.getTables().get(currentTableIndex).getOrderCount();
			

		
			Button btn[] = new Button[orderCount];


			if (null != layout && layout.getChildCount() > 0) {                 
				try {
					layout.removeViews (0, layout.getChildCount());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


			
			//Handler for dynamic order buttons, passes in the table index / ID and order ID
			View.OnClickListener btnHandler = new View.OnClickListener() {
				public void onClick(View v) {
					String[] tags = (String[]) v.getTag();
					Button orderButton = (Button)v;
					String buttonText = orderButton.getText().toString();
					Intent intent = new Intent(v.getContext(), OrderDetails.class);
					intent.putExtra("tableIndex", Integer.parseInt(tags[0]));
					intent.putExtra("orderNumber", Integer.parseInt(tags[1]));
					
					startActivity(intent);
				}
			};


			layout = (LinearLayout) findViewById(R.id.orderButtons_list);

			//create the buttons based on the table list and assign the btnHandler to each
			for (int i=0;i<orderCount;i++){
				btn[i] = new Button(this);
				btn[i].setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
				btn[i].setBackgroundResource(R.drawable.large_plate);  // add image for kicks
				btn[i].setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
				btn[i].setText("Order " + (i + 1));
				tag[ORDER_NUMBER] = Integer.toString(i);
				btn[i].setTag(tag.clone());
				btn[i].setOnClickListener(btnHandler);

				layout.addView(btn[i]);
			}
	

	}


}
