package application;

import java.io.Serializable;
import java.util.ArrayList;

public class menuItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int menuID;
	
	private String name = null;
	private String ingrd = null;
	private double port;
	private ArrayList<String> recipeList;
	private double parsValue;
	private ArrayList<Double> portionList;
	
	public menuItem(String item, ArrayList<String> itemList, ArrayList<Double> portions, double pars)
	{
		name = item;
		parsValue = pars;
		recipeList = itemList; 
		portionList = portions;
		menuID = DBController.getMenuItemsSize()+1;
	}
	
	public menuItem(String item, ArrayList<String> itemList, ArrayList<Double> portions)
	{
		name = item;
		parsValue = 1.25;
		recipeList = itemList;
		portionList = portions;
		menuID = DBController.getMenuItemsSize()+1;
	}
	public menuItem(String ingredient, double portion)
	{
		ingrd = ingredient;
		port = portion;
		
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
	public String getItemName(){
		return ingrd;
	}
	public double getItemPortions(){
		return port;
	}
	public void setItemPortions(double newPort){
		port = newPort;
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