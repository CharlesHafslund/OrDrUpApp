import java.util.ArrayList;

import com.google.gson.Gson;



public class testspace {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.print(APIRequestor.get("table", "Username=q.tip&Password=password&RestaurantID=1"));
		//apitest url www.ordrupapp.com/apiTester.php
		//check login request
		System.out.println("login : " + APIRequestor.login("auth_Username=q.tip&auth_Password=password"));
		//login url: www.ordrupapp.com/login, not api
		
		
		//get assigned tables request
		System.out.println("table : " + APIRequestor.get("table", "auth_Username=q.tip&auth_Password=password"));
		
		//get menu request
		//System.out.println("Menu : "+ APIRequestor.get("menuitem", "auth_Username=q.tip&auth_Password=password"));
		//Gson gson = new Gson();
		//ArrayList<ArrayList<menuItem>> myMenu; 
		
		//myMenu = gson.fromJson(APIRequestor.get("menuItem", "auth_Username=q.tip&auth_Password=password"), menuItem[].class);
		
		//myMenu = APIRequestor.jsonToMenuItemArray(APIRequestor.get("menuItem", "auth_Username=q.tip&auth_Password=password"));
		
		//System.out.println("Got this : " + myMenu.get(1).get(0).getName());
		
		//System.out.println(myMenu[0].getName());
		 
		
		//add (create) order request
		System.out.println("Order : "+ APIRequestor.post("order", "auth_Username=q.tip&auth_Password=password&TableID=6"));
		System.out.println(APIRequestor.get("order", "auth_Username=q.tip&auth_Password=password&TableID=6"));
		
		//add (create) orderitem to order request
		//System.out.println("OrderItem : "+ APIRequestor.post("orderitem", "auth_Username=q.tip&auth_Password=password&OrderID=106&MenuItemID&PurchasePrice=1.99"));
		
		//clear table (update) request - checks bill has been paid returns either cleared or not
		
		
		
	}

}
