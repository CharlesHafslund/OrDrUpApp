package com.ordrupapp.ordrup;

import java.util.ArrayList;
import java.util.Collections;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class TableSelect extends Activity {
	LinearLayout layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table_select);
		getTables((View)findViewById(R.id.tableButtons_list));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.table_select, menu);
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.settings_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.settings_logoff:
	        sessionInfo.getInstance().clear();
	        Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
	        startActivity(intent);
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
		//retain buttons when rotating
        super.onConfigurationChanged(newConfig);

    }
	
	
	
	public void getTables(View view) {
		
		
		
		//get a reference to the session info
		sessionInfo mySession = sessionInfo.getInstance();
		
		
		
		
		//if exist in API request and current list, add existing table to dummy
		//if exist in API and not in current list, add new table to dummy
		//else ignore
		//point tables to dummy
		
		//get the updated list of tables from the server, this will only get the tables assigned to the logged in user that are occupied
		ArrayList<table> newTables = APIRequestor.jsonToTableArray(APIRequestor.get("table", "&UserID=" + mySession.getUserID() + "&Status=Occupied"));
		
		for (int i = 0; i < newTables.size(); i++){
			if (mySession.getTables().contains(newTables.get(i))); //skip
			else mySession.getTables().add(newTables.get(i));  //new table, add it
		}

		//sort the tables for easy viewing
		Collections.sort(mySession.getTables());

		int tableCount = mySession.getTables().size();
		
			
		Button btn[] = new Button[tableCount];
		
		if (null != layout && layout.getChildCount() > 0) {                 
			try {
				layout.removeViews (0, layout.getChildCount());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		//Handler for dynamic buttons, passes in the table number
		View.OnClickListener btnHandler = new View.OnClickListener() {
		    public void onClick(View v) {
		        Button tableButton = (Button)v;
		        String buttonText = tableButton.getText().toString();
		        Intent intent = new Intent(v.getContext(), TableDetail.class);
		        intent.putExtra("tableNumber", buttonText);
		        intent.putExtra("tableIndex", Integer.parseInt(tableButton.getTag().toString())); //Tables index in the AL
		        startActivity(intent);
		        
		    }
		};
		
		
		
		layout = (LinearLayout) findViewById(R.id.tableButtons_list);
		
		//create the buttons based on the table list and assign the btnHandler to each
		for (int i=0;i<tableCount;i++){
			btn[i] = new Button(this);
			btn[i].setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			btn[i].setBackgroundResource(R.drawable.table);  // add image for kicks
			btn[i].setTextColor(Color.WHITE);
			btn[i].setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
			btn[i].setText(Integer.toString(mySession.getTables().get(i).getTableNumber()));
			btn[i].setOnClickListener(btnHandler);
			btn[i].setTag(i);

			layout.addView(btn[i]);
		}
		
	}

}
