package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class manageController 
{
	Parent root;
	Stage manageStage = new Stage();
	ObservableList<inventoryItem> data = DBController.getInventory();
	ObservableList<menuItem> dataMenu = DBController.getMenu();
	ObservableList<menuItem> loadedData = DBController.getMenu();
	
	int currentItemID;
	
		@FXML
	    private VBox vBoxManageFrame;
		
		@FXML
	    private VBox inventoryItemVBox;
		
		@FXML
	    private VBox menuItemVBox;
		
	    @FXML
	    private Button invItems;

	    @FXML
	    private Button menuItemsList;

	    @FXML
	    private MenuButton menuSelector;

	    @FXML
	    private MenuItem menuItemView;

	    @FXML
	    private TableView<inventoryItem> mainTable;
	    
	    @FXML
	    private TableView<menuItem> menuTable;

	    @FXML
	    private MenuItem item1;
	    
	    @FXML
	    private MenuItem item2;
	    
	    @FXML
	    private GridPane newItemGrid;

	    @FXML
	    private TextField nameField;

	    @FXML
	    private Button addItemButton;
	    @FXML
	    private Button modifyItemButton;
	    @FXML
	    private Button addMenuItemButton;
	    @FXML
	    private Button addIngredientsBtn;
	    
	    @FXML
	    private TextField vendorField;
	    @FXML
	    private TextField stockField;
	    @FXML
	    private TextField unitField;
	    @FXML
	    private TextField stationField;
	    @FXML
	    private TextField orderUnitField;
	    
    @FXML
    void addItem(ActionEvent event) 
    {	
    	DBController.addInventory(nameField.getText(), unitField.getText(), orderUnitField.getText(), vendorField.getText(), data.size()+1, Integer.parseInt(stockField.getText()));
    	inventoryItem item = new inventoryItem(nameField.getText(), data.size()+1, Integer.parseInt(stockField.getText()), vendorField.getText(), unitField.getText(), orderUnitField.getText() );
    	data.add(item);
    	nameField.clear();
    	stockField.clear();
    	vendorField.clear();
    	unitField.clear();
    	orderUnitField.clear();
    }
    @FXML
    void addMenuItem(ActionEvent event) 
    {	
    	
    	ArrayList<String> ingredients = new ArrayList<String>();
    	ArrayList<Double> portions = new ArrayList<Double>();

    	Dialog<ButtonType> dialog = new Dialog<>();
    	Stage dialogStage = (Stage) dialog.getDialogPane().getScene().getWindow();
    	dialog.getDialogPane().setPrefSize(500, 300);
    	dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
    	dialog.getDialogPane().getButtonTypes().add(new ButtonType("Add", ButtonData.OK_DONE));
    	dialog.getDialogPane().getButtonTypes().add(new ButtonType("Cancel", ButtonData.CANCEL_CLOSE));
    	dialog.setTitle("Inventory List");
    	dialog.setHeaderText(null);
    	dialog.setResizable(false);
    	
    	ListView<String> listView = new ListView<>();
    	listView.setPickOnBounds(true);
    	data.forEach(item -> listView.getItems().add(item.itemProperty().get()));
    	
    	listView.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(String item) {
                BooleanProperty observable = new SimpleBooleanProperty();
                for (int i = 0; i < ingredients.size(); i++) {
                    if (item.equals(ingredients.get(i))) {
                        observable.set(true);
                    }
                }
                observable.addListener((obs, notChecked, isChecked) -> {
                    if (isChecked) {
                    	ingredients.add(item);
                    } else {
                    	ingredients.remove(item);
                    }
                });
                return observable;
            }
        }));    	
    		
    	Label name = new Label("Recipe:");
    	TextField nameField = new TextField();
    	
    	GridPane grid = new GridPane();
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(100);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(25);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(25);
    	
    	grid.add(new Label("Select Ingredients"), 0, 0);
    	grid.add(listView, 0, 1);
    	grid.add(name, 0, 2);
    	grid.add(nameField, 0, 2);

    	dialog.getDialogPane().setContent(grid);
    	
    	Optional<ButtonType> result = dialog.showAndWait();
    	 if (result.isPresent() && result.get().getButtonData() == ButtonData.OK_DONE)
    	{
    		System.out.println("Thank you");
    		ingredients.forEach(item -> portions.add(0.0));
    		menuItem food = new menuItem(nameField.getText(), ingredients, portions);
    		DBController.addMenuItem(food.getID(), food);
    		dataMenu.add(food);
    	} 
    	else 
    	{
    		dialog.close();
    	}
    }
    @FXML
    void selectInvItems(ActionEvent event) throws SQLException 
    {
    	ContextMenu contextMenu = new ContextMenu();
    	MenuItem deleteItemMenu = new MenuItem("Delete");
    	MenuItem modifyMenu = new MenuItem("Modify");
    	EventHandler<ActionEvent> deleteEvent = deleteItem();
    	EventHandler<ActionEvent> modify = modify();
    	contextMenu.getItems().add(deleteItemMenu);
    	contextMenu.getItems().add(modifyMenu);
    	deleteItemMenu.setOnAction(deleteEvent);
    	modifyMenu.setOnAction(modify);
    	
    	menuItemVBox.setVisible(false);
    	inventoryItemVBox.setVisible(true);
    	mainTable.getColumns().clear();
    	mainTable.setContextMenu(contextMenu);
    	
    	
    	TableColumn<inventoryItem, String> itemNameCol = new TableColumn<inventoryItem, String>("Inventory Item");
    	itemNameCol.setMinWidth(200);
    	itemNameCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, String>("item"));
    	
    	TableColumn<inventoryItem, Integer> itemNumberCol = new TableColumn<inventoryItem, Integer>("ID");
    	itemNumberCol.setMinWidth(50);
    	itemNumberCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, Integer>("productID"));
    	
    	TableColumn<inventoryItem, String> itemUnitCol = new TableColumn<inventoryItem, String>("Unit");
    	itemUnitCol.setMinWidth(50);
    	itemUnitCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, String>("unit"));
    	
    	TableColumn<inventoryItem, String> itemOrderUnitCol = new TableColumn<inventoryItem, String>("OrderUnit");
    	itemOrderUnitCol.setMinWidth(50);
    	itemOrderUnitCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, String>("orderunit"));
    	
    	TableColumn<inventoryItem, Integer> itemVendorCol = new TableColumn<inventoryItem, Integer>("Vendor");
    	itemVendorCol.setMinWidth(50);
    	itemVendorCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, Integer>("distributor"));
    	
    	mainTable.setItems(data);
    	mainTable.getColumns().addAll(itemNumberCol, itemNameCol, itemUnitCol,itemOrderUnitCol, itemVendorCol);

    }    
    @FXML
    void selectMenuItems(ActionEvent event) 
    {
    	ContextMenu contextMenu = new ContextMenu();
    	MenuItem deleteItemMenu = new MenuItem("Delete");
    	EventHandler<ActionEvent> deleteEvent = deleteMenuItem();
    	contextMenu.getItems().add(deleteItemMenu);
    	deleteItemMenu.setOnAction(deleteEvent);
    	
    	inventoryItemVBox.setVisible(false);
    	menuItemVBox.setVisible(true);
    	addMenuItemButton.setVisible(true);
    	menuTable.getColumns().clear();
    	menuTable.setContextMenu(contextMenu);
    	
    	TableColumn<menuItem, String> menuItemName = new TableColumn<menuItem, String>("Recipe");
    	menuItemName.setMinWidth(200);
    	menuItemName.setCellValueFactory(new PropertyValueFactory<menuItem, String>("name"));
    	
//    	TableColumn<menuItem, Double> parsCol = new TableColumn<menuItem, Double>("PARS Value");
//    	parsCol.setMinWidth(50);
//    	parsCol.setCellValueFactory(new PropertyValueFactory<menuItem, Double>("pars"));
    	
    	TableColumn<menuItem, String> ingredients = new TableColumn<>("Ingredients");
    	
    	TableColumn<menuItem, String> ingrdName = new TableColumn<>("Name");
    	ingrdName.setMinWidth(200);
    	ingrdName.setCellValueFactory(new PropertyValueFactory<menuItem, String>("itemName"));
    	ingrdName.setSortable(false);
    	TableColumn<menuItem, Double> ingrdPortion = new TableColumn<>("Portion");
    	ingrdPortion.setMinWidth(50);
    	ingrdPortion.setSortable(false);
    	ingrdPortion.setCellValueFactory(new PropertyValueFactory<menuItem, Double>("portion"));
    	
    	TableColumn actionCol = new TableColumn("");
    	actionCol.setMinWidth(50);
    	actionCol.setSortable(false);
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<menuItem, String>, TableCell<menuItem, String>> cellFactory
                = new Callback<TableColumn<menuItem, String>, TableCell<menuItem, String>>() {
            @Override
            public TableCell call(final TableColumn<menuItem, String> param) {
                final TableCell<menuItem, String> cell = new TableCell<menuItem, String>() {

                    final ToggleButton btn = new ToggleButton("+"); 
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                        	
                        		btn.setOnAction(event -> {
                        			if(btn.isSelected())
                        			{
                        				
                        				menuItem ingrd = getTableView().getItems().get(getIndex());
                                    	int i = 0;
                                    	while(i<ingrd.getRecipe().size())
                                    	{
                                    		String name = ingrd.getRecipe().get(i);
                                    		double portion = ingrd.getPortions().get(i);
                                    		menuItem newIngrd = new menuItem(name, portion);
                                    		dataMenu.add(getIndex()+i+1, newIngrd);                        		
                                    		i++;
                                    	}
                                    	
                                    	if(!ingredients.getColumns().contains(ingrdName))
                                    	{
                                    		ingredients.getColumns().addAll(ingrdName, ingrdPortion);
                                    	}
                                    	btn.setText("-");
                        			}
                        			else
                        			{
                        				menuItem ingrd = getTableView().getItems().get(getIndex());
                        				int i = ingrd.getRecipe().size();
                                    	while(i>0)
                                    	{
                                    		
                                    		dataMenu.remove(getIndex()+i);
                                    		i--;
                                    	}
                                    	btn.setText("+");
                        			}
                                	
                                	
                        	}
                        			
                            );
                        	if(!dataMenu.isEmpty()&&dataMenu.get(this.getTableRow().getIndex()).getName()==null)
                        	{
                        		setGraphic(null);
                                setText(null);

                        	}
                        		setGraphic(btn);
                                setText(null);                 	
                        }
                    }
                };
                return cell;
            }
        };
        actionCol.setCellFactory(cellFactory);
    	ingredients.getColumns().addAll(actionCol);
	
    	menuTable.getColumns().addAll(menuItemName, ingredients);
    	menuTable.setItems(dataMenu);

    }    
    @FXML
    void modifyItem(ActionEvent event) 
    {
    	
    	inventoryItem current = mainTable.getSelectionModel().getSelectedItem();
		currentItemID = current.productIDProperty().get();
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Do you wish to modify this item?");
    	Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();	
    	alertStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
    	alert.setTitle("Item Modification Confirmation");
    	alert.getDialogPane().setHeader(null);
    	alert.setHeaderText(null);
    	alert.setGraphic(null);
    
    	
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK)
    	{
    		current.itemProperty().set(nameField.getText());
    		current.unitProperty().set(unitField.getText());
    		current.orderunitProperty().set(orderUnitField.getText());
    		current.distributorProperty().set(vendorField.getText());
    		current.quantityProperty().set(Integer.parseInt(stockField.getText()));
    		DBController.modifyInventory(nameField.getText(), unitField.getText(), orderUnitField.getText(), vendorField.getText(), currentItemID, Integer.parseInt(stockField.getText()));
    		
    	} 
    	else 
    	{
    		alert.close();
    	}
    }  
    EventHandler<ActionEvent> modify(){
    	return new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
				inventoryItem current = mainTable.getSelectionModel().getSelectedItem();
		    	nameField.setText(current.itemProperty().get());
		    	stockField.setText(""+current.quantityProperty().get());;
		    	vendorField.setText(current.distributorProperty().get());
		    	unitField.setText(current.unitProperty().get());
		    	orderUnitField.setText(current.orderunitProperty().get());
		}
	};
    }
    EventHandler<ActionEvent> deleteItem(){
    	return new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				inventoryItem current = mainTable.getSelectionModel().getSelectedItem();
				currentItemID = current.productIDProperty().get();
		    	Alert alert = new Alert(AlertType.CONFIRMATION, "Do you wish to remove this item from the database?");
		    	Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();	
		    	alertStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
		    	alert.setTitle("Item Deletion Confirmation");
		    	alert.getDialogPane().setHeader(null);
		    	alert.setHeaderText(null);
		    	alert.setGraphic(null);
		    
		    	
		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK)
		    	{
		    		DBController.deleteItem(currentItemID);
		    		data.remove(current);
		    	} 
		    	else 
		    	{
		    		alert.close();
		    	}
		}
	};
	
    }
	EventHandler<ActionEvent> deleteMenuItem(){
    	return new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				menuItem current = menuTable.getSelectionModel().getSelectedItem();
				currentItemID = current.getID();

				if ((Integer.toString(current.getID()))!=null)
				{
					Alert alert = new Alert(AlertType.CONFIRMATION, "Do you wish to remove this menu item from the database?");
			    	Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();	
			    	alertStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
			    	alert.setTitle("Menu Deletion Confirmation");
			    	alert.getDialogPane().setHeader(null);
			    	alert.setHeaderText(null);
			    	alert.setGraphic(null);
			    
			    	
			    	Optional<ButtonType> result = alert.showAndWait();
			    	if (result.get() == ButtonType.OK)
			    	{
			    		;
			    		int i = current.getPortions().size();
			    		int j = current.getID();
			    		DBController.deleteMenuItem(j);
			    		dataMenu.remove(j, i+j);
			    	} 
			    	else 
			    	{
			    		alert.close();
			    	}
				}
				else
				{
					Alert alert = new Alert(AlertType.CONFIRMATION, "Do you wish to remove this menu item from the database?");
			    	Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();	
			    	alertStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
			    	alert.setTitle("Menu Deletion Confirmation");
			    	alert.getDialogPane().setHeader(null);
			    	alert.setHeaderText(null);
			    	alert.setGraphic(null);
			    
			    	
			    	Optional<ButtonType> result = alert.showAndWait();
			    	if (result.get() == ButtonType.OK)
			    	{
			    		dataMenu.remove(current);
			    	} 
			    	else 
			    	{
			    		alert.close();
			    	}
				}
		    	
		}
	};	

    }  
		
    @FXML
    void selectMenuItemView(ActionEvent event) 
    {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewInventory.fxml"));
			root = loader.load();
			loader.setController("viewController");
            manageStage.setTitle("Denunzio's Inventory Management Tools");
            manageStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
            manageStage.setScene(new Scene(root, 900, 600));
            manageStage.setResizable(false);
            manageStage.show();
            Stage stage = (Stage) vBoxManageFrame.getScene().getWindow();
            stage.hide();
        }
        catch (IOException e) 
		{
            e.printStackTrace();
		}
    }  
    

}
