package application;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CSVReader {
	
	public static String readDate(File filePath) {
		try (Scanner in = new Scanner(filePath)){
			return in.nextLine();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ObservableList<inventoryItem> read(File filePath){
		ArrayList<inventoryItem> inventory = new ArrayList();
		try (Scanner in = new Scanner(filePath)){
			in.nextLine(); //Ignore first line, is is the date line
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
