package application;

import java.io.Serializable;
import java.util.ArrayList;

public class menuItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int menuID;
	
	private String name = null;
	private String itemType = null;
	private ArrayList<String> recipeList;
	private double parsValue;
	private ArrayList<Double> portionList;
	
	public menuItem(String item, String itemKey, ArrayList<String> itemList, ArrayList<Double> portions, double pars)
	{
		name = item;
		itemType = itemKey;
		parsValue = pars;
		recipeList = itemList; 
		portionList = portions;
		menuID = DBController.getMenuItemsSize()+1;
	}
	
	public menuItem(String item, String itemKey, ArrayList<String> itemList, ArrayList<Double> portions)
	{
		name = item;
		itemType = itemKey;
		parsValue = 1.25;
		recipeList = itemList;
		portionList = portions;
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
	
	public double parsValue() 
	{
		return parsValue;
	}
	
	public void setparsValue(double pars) 
	{
		parsValue = pars;
	}
	
	public ArrayList<String> getRecipe() 
	{
		return recipeList;
	}
	
	public ArrayList<Double> getPortions(){
		return portionList;
	}
	
	public boolean addMenuIngrd(String ingrd, double portion) 
	{
		recipeList.add(ingrd);
		portionList.add(portion);
		return true;
	}
	
	public boolean addMenuIngrd(inventoryItem ingrd, double portion) {
		recipeList.add(ingrd.itemProperty().get());
		portionList.add(portion);
		return true;
	}
	
	public int getID() 
	{
		return menuID;
	}
	
	
	menuItem()
	{
		
	}
}