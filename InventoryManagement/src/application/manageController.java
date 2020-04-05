package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private TableView mainTable;

    @FXML
    private Button vendorsList;
    
    @FXML
    private ContextMenu contextMenu;
    
    @FXML
    private MenuItem item1;
    
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
    private GridPane newItemGrid;

    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private TextField stockField;
    
    @FXML
    void addItem(ActionEvent event) 
    {	
    	DBController.addInventory(nameField.getText(), null, null, null, Integer.parseInt(idField.getText()), Integer.parseInt(stockField.getText()));
    	inventoryItem item = new inventoryItem(nameField.getText(), Integer.parseInt(idField.getText()), Integer.parseInt(stockField.getText()), null/**vendorField.getText()**/);
    	data.add(item);
    	nameField.clear();
    	idField.clear();
    	stockField.clear();
    }
    @FXML
    private Button addItemButton;
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
    	
    	TableColumn<inventoryItem, Integer> itemStockCol = new TableColumn<inventoryItem, Integer>("Current Stock");
    	itemStockCol.setMinWidth(100);
    	itemStockCol.editableProperty();
    	itemStockCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, Integer>("quantity"));
    	
    	TableColumn<inventoryItem, Integer> itemVendorCol = new TableColumn<inventoryItem, Integer>("Vendor");
    	itemVendorCol.setMinWidth(50);
    	itemVendorCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, Integer>("distributor"));
    	
    	mainTable.setItems(data);
    	mainTable.getColumns().addAll(itemNumberCol, itemNameCol, itemStockCol);
    	
    	
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
