package application;

import java.io.Serializable;
import java.util.ArrayList;

public class menuItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int menuID;
	
	private String name = null;
	private String itemName = null;
	private double portion;
	private ArrayList<String> recipeList;
	private double pars;
	private ArrayList<Double> portionList;
	
	public menuItem(String item, ArrayList<String> itemList, ArrayList<Double> portions, double parsValue)
	{
		name = item;
		pars = parsValue;
		recipeList = itemList; 
		portionList = portions;
		menuID = DBController.getMenuItemsSize()+1;
	}
	
	public menuItem(String item, ArrayList<String> itemList, ArrayList<Double> portions)
	{
		name = item;
		pars = 1.25;
		recipeList = itemList;
		portionList = portions;
		menuID = DBController.getMenuItemsSize()+1;
	}
	public menuItem(String ingredient, double portionNum)
	{
		itemName = ingredient;
		portion = portionNum;
		
	}
	public String getName() 
	{
		return name;
	}
	
	public void setName(String item) 
	{
		name = item;
	}
	
	public double parsValue() 
	{
		return pars;
	}
	
	public void setparsValue(double pars) 
	{
		pars = pars;
	}
	
	public ArrayList<String> getRecipe() 
	{
		return recipeList;
	}
	
	public ArrayList<Double> getPortions(){
		return portionList;
	}
	public String getItemName(){
		return itemName;
	}
	public double getItemPortions(){
		return portion;
	}
	public void setItemPortions(double newPort){
		portion = newPort;
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