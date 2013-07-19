package com.ordrupapp.ordrup;

import java.util.ArrayList;

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
		sessionInfo mySession = sessionInfo.INSTANCE;
		
		//need to make a call here to get tables from API
		ArrayList<table> tables = mySession.getTables();
		tables = new ArrayList<table>();
		tables.add(new table(111,1));
		tables.add(new table(121,2));
		tables.add(new table(113,7));
		tables.add(new table(131,9));
		
		
		
		int tableCount = tables.size();
		
		
		//debug message
    	Toast.makeText(getApplicationContext(), "SiteCode: " + mySession.getSitecode() + "\nUsername: " + mySession.getUsername() + "\nPassword: " + mySession.getPasswordHash(), Toast.LENGTH_LONG).show();
    	
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
		        intent.putExtra("table", buttonText);
		        intent.putExtra("index", tableButton.getTag().toString()); //get the array index somehow
		        
		      //debug message
		    	Toast.makeText(getApplicationContext(), "Tag: " + tableButton.getTag().toString(), Toast.LENGTH_LONG).show();
		        
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
			btn[i].setText(Integer.toString(tables.get(i).getTableNumber()));
			btn[i].setOnClickListener(btnHandler);
			btn[i].setTag(i);

			layout.addView(btn[i]);
		}
		
	}

}
