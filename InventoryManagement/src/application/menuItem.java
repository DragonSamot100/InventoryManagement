package application;
import java.util.ArrayList;

public class menuItem 
{
	private ArrayList<inventoryItem> itemList;
	private int menuID;
	private static int menuIDcount = 0;
	private String name;
	
	
	public menuItem(String itemName, ArrayList<inventoryItem> list){
		name = itemName;
		menuID = menuIDcount ++;
		itemList = list;
	}
	
	public int getID() {
		return menuID;
	}
}
