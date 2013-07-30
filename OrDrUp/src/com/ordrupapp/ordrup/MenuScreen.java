package com.ordrupapp.ordrup;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MenuScreen extends FragmentActivity implements
		ActionBar.OnNavigationListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";


	private static int orderNumber, currentTableIndex;
	private static String[] tag = new String[6];
	
	private final int TABLE_INDEX = 0,
						ORDER_NUMBER = 1,
						MENU_TYPE = 2,
						MENU_INDEX = 3,
						NOTES = 4;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_screen);
		StrictMode.ThreadPolicy policy = new StrictMode.
				ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);

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
		//set the table number and order number for the screen

		currentTableIndex = getIntent().getIntExtra("table", 0);
		orderNumber =  getIntent().getIntExtra("orderNumber", 0);
		
		tag[TABLE_INDEX] = Integer.toString(currentTableIndex);
		tag[ORDER_NUMBER] = Integer.toString(orderNumber);
		
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
		
		private final int TABLE_INDEX = 0,
				ORDER_NUMBER = 1,
				MENU_TYPE = 2,
				MENU_INDEX = 3,
				NOTES = 4;
		

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
			
			tag[MENU_TYPE] = Integer.toString(section);
			
			
			//add the menu items to the screen for the current section
			TextView vName,vPrice;
			//EditText vNotes;
			Button addItem;
			final ArrayList<EditText> vNotes = new ArrayList<EditText>();
			
			//Handler for dynamic buttons
			View.OnClickListener btnHandler = new View.OnClickListener() {
			    public void onClick(View v) {
			        
			        String[] tags = (String[]) v.getTag();
			        
			        sessionInfo.getInstance().
			        			getTables().									//from my list of tables
			        			get(Integer.parseInt(tags[0])).					//for the current table
			        			addOrderItem(Integer.parseInt(tags[1]), 		//to specified order index
			        							Integer.parseInt(tags[2]), 		//add an item of type
			        							Integer.parseInt(tags[3]),		//with type specific index 
			        							vNotes.get(Integer.parseInt(tags[3])).getText().toString()); //notes
			        
			    }
			};
			
			//add the children
			for (int i = 0; i < menu.INSTANCE.getMenuItemList(section).size(); i++){
				vName = new TextView(container.getContext());
				vName.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
				vName.setText(menu.INSTANCE.getMenuItemList(section).get(i).getName());
								
				vPrice = new TextView(container.getContext());
				vPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
				vPrice.setText(Double.toString(menu.INSTANCE.getMenuItemList(section).get(i).getPrice()));
				
				vNotes.add(new EditText(container.getContext()));
				vNotes.get(i).setTextSize(TypedValue.COMPLEX_UNIT_SP,24);

				addItem = new Button(container.getContext());
				addItem.setText("+");
				addItem.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
				tag[MENU_INDEX] = Integer.toString(i);
				addItem.setTag(tag.clone());
				addItem.setOnClickListener(btnHandler);
				layout.addView(addItem);
				layout.addView(vName);
				layout.addView(vNotes.get(i));
				layout.addView(vPrice);
				
				
			}
						
			return rootView;
		
		}
		
	}

}
