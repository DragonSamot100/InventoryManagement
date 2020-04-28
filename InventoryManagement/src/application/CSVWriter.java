package application;

import java.io.*;
import java.util.ArrayList;

public class CSVWriter {
	
	public static boolean write(File filePath) {
		try (PrintWriter out = new PrintWriter(filePath)){
			out.println(); //Something with date here
			ArrayList<inventoryItem> inventory = DBController.getInventoryAsArray();
			for (inventoryItem item : inventory) {
				String toAdd = "" + item.productIDProperty().get();
				toAdd += "," + item.itemProperty().get();
				toAdd += "," + item.quantityProperty().get();
				toAdd += "," + item.distributorProperty().get();
				toAdd += "," + item.unitProperty().get();
				toAdd += "," + item.orderunitProperty().get();
				
			}
			
			
			
			
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
