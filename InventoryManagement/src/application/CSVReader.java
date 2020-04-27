package application;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CSVReader {
	
	public static ObservableList<inventoryItem> read(String filePath){
		ArrayList<inventoryItem> inventory = new ArrayList();
		try (Scanner in = new Scanner(filePath)){
			in.nextLine(); //Something with date here
			String line;
			while(in.hasNextLine()) {
				line = in.nextLine();
				String[] split = line.split(",");
				inventoryItem item = new inventoryItem(split[1], Integer.parseInt(split[0]), Integer.parseInt(split[2]), split[3], split[4], split[5]);
				inventory.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FXCollections.observableArrayList(inventory);
	}
	
	
	
}
