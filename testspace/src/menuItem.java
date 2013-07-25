

public class menuItem {
	private int menuItemID;
	private String name;
	private double price;
	
	/*
	 * Category: "Appetizer"
MenuItemID: "158"
Name: "Chips and Salsa"
PrepTime: "4"
Price: "5.99"
RestaurantID: "22"
	 */
	menuItem(){}
	
	menuItem(int menuItemID, String name, double price){
		this.menuItemID = menuItemID;
		this.name = name.toString();
		this.price = price;
		
	}
	
	public int getMenuItemID(){
		return menuItemID;
	}
	
	public String getName(){
		return name.toString();
	}
	
	public double getPrice(){
		return price;
	}
	
}
