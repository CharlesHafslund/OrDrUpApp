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
		getMenuInflater().inflate(R.menu.table_select, menu);
		return true;
	}
	
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
		//retain buttons when rotating
        super.onConfigurationChanged(newConfig);

    }
	
	public void getTables(View view) {
		
		//get a reference to the session info
		sessionInfo mySession = sessionInfo.getInstance();
		
		
		
		//need to make a call here to get tables from API, this should be a find and add it not exist
		//if exist in API request and current list, add existing table to dummy
		//if exist in API and not in current list, add new table to dummy
		//else ignore
		//point tables to dummy
		
		
		mySession.getTables().add(new table(111,1));
		
		mySession.getTables().add(new table(113,7));
		mySession.getTables().add(new table(131,9));
		mySession.getTables().add(new table(121,2));
		
		
		//sort the tables for easy viewing
		//Collections.sort(tables);
		
		//debug message
    	//Toast.makeText(getApplicationContext(), menu.INSTANCE.getDebugST(), Toast.LENGTH_LONG).show();
    	
		
		
		
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
		        
		        
		      //debug message
		    	Toast.makeText(getApplicationContext(), "Tag: " + tableButton.getTag().toString(), Toast.LENGTH_SHORT).show();
		        
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
