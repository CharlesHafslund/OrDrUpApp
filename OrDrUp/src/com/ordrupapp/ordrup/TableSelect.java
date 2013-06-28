package com.ordrupapp.ordrup;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class TableSelect extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table_select);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.table_select, menu);
		return true;
	}
	
	public void getTables(View view) {
	String tables[] = {"1","2","7","9"};
	int tableCount = tables.length;
    Button btn[] = new Button[tableCount];
    LinearLayout layout = (LinearLayout) findViewById(R.id.tableButtons_list);

    for (int i=0;i<tableCount;i++){
        btn[i] = new Button(this);
        btn[i].setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        btn[i].setText(tables[i]);
        layout.addView(btn[i]);
    }
	}

}
