package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class manageController 
{
	Parent root;
	Stage manageStage = new Stage();
	ObservableList<inventoryItem> data = DBController.getInventory();
	 @FXML
	    private VBox vBoxManageFrame;

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
	    private ContextMenu contextMenu;

	    @FXML
	    private MenuItem item1;

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
    	DBController.addInventory(nameField.getText(), null, null, null, data.size()+1, Integer.parseInt(stockField.getText()));
    	inventoryItem item = new inventoryItem(nameField.getText(), data.size()+1, Integer.parseInt(stockField.getText()), null/**vendorField.getText()**/, null, null);
    	data.add(item);
    	nameField.clear();

    	stockField.clear();
    }
    @FXML
    void menuItemSelected(ActionEvent event) 
    {
    	//supposed to close current window
    }

    @FXML
    void selectInvItems(ActionEvent event) throws SQLException 
    {
    	
    	newItemGrid.setVisible(true);
    	
    	
    	mainTable.getColumns().clear();

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
    	mainTable.getColumns().clear();
    	TableColumn menuItem = new TableColumn("Product Name");
        menuItem.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn idNum = new TableColumn("ID");
        idNum.setCellValueFactory(new PropertyValueFactory<>("id"));

        mainTable.getColumns().addAll(idNum, menuItem);

    }

    @FXML
    void selectVendors(ActionEvent event) 
    {
    	mainTable.getColumns().clear();
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

}
