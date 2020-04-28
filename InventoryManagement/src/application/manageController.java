package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class manageController 
{
	Parent root;
	Stage manageStage = new Stage();
	ObservableList<inventoryItem> data = DBController.getInventory();
	ObservableList<menuItem> dataMenu = DBController.getMenu();
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
	    private Button vendorsList;

	    @FXML
	    private Button menuItemsList;

	    @FXML
	    private MenuButton menuSelector;

	    @FXML
	    private MenuItem MenuItemMenu;

	    @FXML
	    private MenuItem menuItemPARS;

	    @FXML
	    private MenuItem menuItemView;

	    @FXML
	    private TableView<inventoryItem> mainTable;
	    
	    @FXML
	    private TreeTableView<menuItem> treeTable;

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
    void menuItemSelected(ActionEvent event) 
    {
    	//supposed to close current window
    }

    @FXML
    void selectInvItems(ActionEvent event) throws SQLException 
    {
    	ContextMenu contextMenu = new ContextMenu();
    	MenuItem deleteItemMenu = new MenuItem("Delete");
    	EventHandler<ActionEvent> deleteEvent = deleteItem();
    	contextMenu.getItems().add(deleteItemMenu);
    	deleteItemMenu.setOnAction(deleteEvent);
    	
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
    	inventoryItemVBox.setVisible(false);
    	menuItemVBox.setVisible(true);
    	mainTable.getColumns().clear();
    	mainTable.setContextMenu(null);
    	
    	TreeTableColumn<menuItem, String> menuItemName = new TreeTableColumn<>("Product Type");
    	TreeTableColumn<menuItem, String> menuItemType = new TreeTableColumn<>("Menu Item");

    	menuItemName.setCellValueFactory(new TreeItemPropertyValueFactory<>("item"));
    	menuItemType.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemType"));
    	int i = 0;
    	while(dataMenu.size()>=i)
    	{
    		TreeItem<w> root = new TreeItem<String>("Root Node");
    		TreeItem food = new TreeItem.(dataMenu.get(0));;
    		
    		i++;
    	}
    	
    	treeTable.getColumns().addAll(menuItemName, menuItemType);

    }

    @FXML
    void selectVendors(ActionEvent event) 
    {
    	mainTable.getColumns().clear();
    	mainTable.setContextMenu(null);
    	TableColumn vendorName = new TableColumn("Vendor Name");
        vendorName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn idNum = new TableColumn("ID");
        idNum.setCellValueFactory(new PropertyValueFactory<>("id"));

        mainTable.getColumns().addAll(idNum, vendorName);

    }
    
    @FXML
    void selectMenuItemMenu(ActionEvent event) 
    {
    	//switches to Menu management
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/menuManage.fxml"));
			root = loader.load();
			loader.setController("menuController");
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

    @FXML
    void selectMenuItemPARS(ActionEvent event) 
    {
    	//switches to PARS management
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/parsSettings.fxml"));
			root = loader.load();
			loader.setController("parsController");
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
    @FXML
    void getRowClicked(ActionEvent event) 
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
    @FXML
    void modifyItem(ActionEvent event) 
    {
//    	Alert alert = new Alert(AlertType.CONFIRMATION);
//    	alert.setTitle("Inventory Item Deletion");
//    	alert.setHeaderText("Do you wish to delete this item?");
//    	alert.setContentText("INSERTITEMNAMEHEREWHENEVERIGETITTOWORK");
//
//    	ButtonType buttonTypeOne = new ButtonType("One");
//    	ButtonType buttonTypeTwo = new ButtonType("Two");
//    	ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
//
//    	alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
//
//    	Optional<ButtonType> result = alert.showAndWait();
//    	if (result.get() == buttonTypeOne){
//    	    // ... user chose "One"
//    	} else if (result.get() == buttonTypeTwo) {
//    	    // ... user chose "Two"
//    	} else {
//    	    // ... user chose CANCEL or closed the dialog
//    	}
    }  

}
