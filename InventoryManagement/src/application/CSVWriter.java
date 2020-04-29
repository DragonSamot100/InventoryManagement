package application;

import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class CSVWriter {
	
	public static boolean write(File filePath) {
		GregorianCalendar cal = new GregorianCalendar();
		try (PrintWriter out = new PrintWriter(filePath)){
			out.println(cal.getTime()); //Something with date here
			ArrayList<inventoryItem> inventory = DBController.getInventoryAsArray();
			for (inventoryItem item : inventory) {
				String toAdd = "" + item.productIDProperty().get();
				toAdd += "," + item.itemProperty().get();
				toAdd += "," + item.quantityProperty().get();
				toAdd += "," + item.distributorProperty().get();
				toAdd += "," + item.unitProperty().get();
				toAdd += "," + item.orderunitProperty().get();
				out.println(toAdd);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
