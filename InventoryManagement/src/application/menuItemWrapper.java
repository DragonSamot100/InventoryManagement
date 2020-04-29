package application;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class menuItemWrapper {
	private ObservableList<inventoryItem> itemList;
	private ObservableList<Double> portionList;
	private DoubleProperty pars;
	private DoubleProperty portion;
	private StringProperty itemName;
	private StringProperty name;
	private IntegerProperty idProperty;
	
	public menuItemWrapper(menuItem m) {
		name = new SimpleStringProperty(m.getName());
		idProperty = new SimpleIntegerProperty(m.getID());
		pars = new SimpleDoubleProperty(m.parsValue());
		itemName = new SimpleStringProperty(m.getItemName());
		portion = new SimpleDoubleProperty(m.getItemPortions());
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

	public DoubleProperty parsProperty() {
		return pars;
	}

	public StringProperty nameProperty() {
		return name;
	}

	public IntegerProperty idProperty() {
		return idProperty;
	}
	public StringProperty itemNameProperty() {
		return itemName;
	}
	public DoubleProperty portionProperty() {
		return portion;
	}
}
