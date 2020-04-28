package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class menuItemWrapper {
	private ObservableList<inventoryItem> itemList;
	private ObservableList<Double> portionList;
	private DoubleProperty parProperty;
	private StringProperty nameProperty;
	private IntegerProperty idProperty;
	
	public menuItemWrapper(menuItem m) {
		nameProperty = new SimpleStringProperty(m.getName());
		idProperty = new SimpleIntegerProperty(m.getID());
		parProperty = new SimpleDoubleProperty(m.parsValue());
		ArrayList<inventoryItem> items = new ArrayList();
		for (String s : m.getRecipe()) {
			items.add(DBController.getItem(s));
		}
		itemList = FXCollections.observableArrayList(items);
		portionList = FXCollections.observableArrayList(m.getPortions());
	}

	public ObservableList<inventoryItem> getItemList() {
		return itemList;
	}

	public ObservableList<Double> getPortionList() {
		return portionList;
	}

	public DoubleProperty getParProperty() {
		return parProperty;
	}

	public StringProperty getNameProperty() {
		return nameProperty;
	}

	public IntegerProperty getIdProperty() {
		return idProperty;
	}
	
	
}
