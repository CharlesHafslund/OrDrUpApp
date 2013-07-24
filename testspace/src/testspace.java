
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
		System.out.println("table : " + APIRequestor.get("table", "auth_Username=q.tip&auth_Password=password&UserID=158"));
		
		//get menu request
		System.out.println("Menu : "+ APIRequestor.get("menuitem", "auth_Username=q.tip&auth_Password=password"));
		
		//add (create) order request
		System.out.println("Order : "+ APIRequestor.post("order", "auth_Username=q.tip&auth_Password=password&TableID=106"));
		
		//add (create) orderitem to order request
		System.out.println("OrderItem : "+ APIRequestor.post("orderitem", "auth_Username=q.tip&auth_Password=password&OrderID=106&MenuItemID&PurchasePrice=1.99"));
		
		//clear table (update) request - checks bill has been paid returns either cleared or not
		
		
		
	}

}
