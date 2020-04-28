package application;

import java.io.Serializable;
import java.util.ArrayList;

public class menuItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int menuID;
	
	private String name = null;
	private String itemType = null;
	private ArrayList<String> srcList;
	private double parsValue;
	
	public menuItem(String item, String itemKey, ArrayList<String> itemList, double pars)
	{
		this.name = item;
		this.itemType = itemKey;
		this.parsValue = pars;
		this.srcList = itemList; 
		menuID = DBController.getMenuItemsSize()+1;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String item) 
	{
		name = item;
	}
	public String itemType() 
	{
		return itemType;
	}
	public void setitemType(String itemKey) 
	{
		itemType = itemKey;
	}
	public String parsValue() 
	{
		return itemType;
	}
	public void setparsValue(double pars) 
	{
		parsValue = pars;
	}
	public ArrayList<String> getMenuItemList() 
	{
		return srcList;
	}
	public void addMenuIngrd(String ingrd) 
	{
		srcList.add(ingrd);
	}
	public int getID() 
	{
		return menuID;
	}
	
	menuItem()
	{
		
	}
}