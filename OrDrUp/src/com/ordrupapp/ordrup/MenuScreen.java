package com.ordrupapp.ordrup;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.text.Layout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenuScreen extends FragmentActivity implements
		ActionBar.OnNavigationListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";


	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_screen);

		// Set up the action bar to show a dropdown list.
		final ActionBar actionBar = getActionBar();
		actionBar.setTitle("Menu");
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		// Show the Up button in the action bar.
		actionBar.setDisplayHomeAsUpEnabled(true);

		// Set up the dropdown list navigation in the action bar.
		actionBar.setListNavigationCallbacks(
		// Specify a SpinnerAdapter to populate the dropdown list.
				new ArrayAdapter<String>(getActionBarThemedContextCompat(),
						android.R.layout.simple_list_item_1,
						android.R.id.text1, new String[] {
								getString(R.string.title_section_beverage),
								getString(R.string.title_section_appetizer), 
								getString(R.string.title_section_main_course),
								getString(R.string.title_section_dessert),}), this);
	}
	
	
	/**
	 * Backward-compatible version of {@link ActionBar#getThemedContext()} that
	 * simply returns the {@link android.app.Activity} if
	 * <code>getThemedContext</code> is unavailable.
	 */
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private Context getActionBarThemedContextCompat() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return getActionBar().getThemedContext();
		} else {
			return this;
		}
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current dropdown position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_screen, menu);
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

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		// When the given dropdown item is selected, show its contents in the
		// container view.
		Fragment fragment = new MenuSectionFragment();
		Bundle args = new Bundle();
		args.putInt(MenuSectionFragment.ARG_SECTION_NUMBER, position + 1);
		fragment.setArguments(args);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, fragment).commit();
		return true;
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class MenuSectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		
		
		

		public MenuSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_menu_screen_dummy, container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			
			GridLayout layout = (GridLayout) rootView.findViewById(R.id.menu_item_grid);
			
			//clear the view
			layout.removeAllViews();
			
			layout.setColumnCount(4);
			
			
			//get the section number
			int section = getArguments().getInt(ARG_SECTION_NUMBER) -1;
			
			//add the menu items to the screen for the current section
			
			//add the children
			for (int i = 0; i < menu.INSTANCE.getMenuItemList(section).size(); i++){
				layout.addView(menu.INSTANCE.getMenuItemList(section).get(i).getViewArray(rootView)[0]);
				layout.addView(menu.INSTANCE.getMenuItemList(section).get(i).getViewArray(rootView)[1]);
				layout.addView(menu.INSTANCE.getMenuItemList(section).get(i).getViewArray(rootView)[2]);
				layout.addView(menu.INSTANCE.getMenuItemList(section).get(i).getViewArray(rootView)[3]);
				//menu.INSTANCE.getMenuItemList(section).get(i).getViewArray(rootView)[3].setO
			}
			
			return rootView;
		
		}
		
//		//Handler for dynamic buttons, passes in the table number
//				View.OnClickListener btnHandler = new View.OnClickListener() {
//				    public void onClick(View v) {
//				        Button tableButton = (Button)v;
//				        String buttonText = tableButton.getText().toString();
//				        Intent intent = new Intent(v.getContext(), TableDetail.class);
//				        intent.putExtra("item", "info");
//				        startActivity(intent);
//				    }
//				};}
				
				
//				layout = (LinearLayout) findViewById(R.id.tableButtons_list);
//				
//				//create the buttons based on the table list and assign the btnHandler to each
//				for (int i=0;i<tableCount;i++){
//					btn[i] = new Button(this);
//					btn[i].setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
//					btn[i].setBackgroundResource(R.drawable.table);  // add image for kicks
//					btn[i].setTextColor(Color.WHITE);
//					btn[i].setTextSize(TypedValue.COMPLEX_UNIT_SP,32);
//					btn[i].setText(tables[i]);
//					btn[i].setOnClickListener(btnHandler);
//
//					layout.addView(btn[i]);
//				}
	}

}
