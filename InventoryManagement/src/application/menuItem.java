package application;

import java.io.Serializable;
import java.util.ArrayList;

public class menuItem<inventoryItem> implements Serializable{
private static final long serialversionUID = 8008L; 
	private ArrayList<inventoryItem> itemList;
	private int menuID;
	private static int menuIDcount = 0;
	private String name, key;
	
	public menuItem(String itemName, String itemKey)
	{
		this.name = itemName;
		menuID = menuIDcount ++;
		key = itemKey;
	}
	menuItem()
	{
		
	}
	
	public String getName() 
	{
		return name;
	}
	public int getID() 
	{
		return menuID;
	}

}