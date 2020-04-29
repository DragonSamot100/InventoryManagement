package application;
import java.io.File;
import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class viewController 
{	
	Parent root;
	Stage manageStage = new Stage();
	
    @FXML
    private Button importButton;
    
    @FXML
    private HBox HBoxParent;
    
    @FXML
    private Button exportButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button selectDateButton;

    @FXML
    private DatePicker getDate;

    @FXML
    private ChoiceBox<?> recentBox;
    
    @FXML
    private MenuButton menuSelector1;
    
    @FXML
    private MenuItem menuItemManage;

    @FXML
    private MenuItem menuItemPARS;
    
    @FXML
    private Label dateLabel;
    
    @FXML
    private TabPane tableTabContainer;
    
    @FXML
    public TableView<inventoryItem> currentInvTable;

    
    @FXML
    void dateSelected(ActionEvent event) 
    {
    	//gets selected date and check if it exists as a saved inventory
    	//selectDate(event);
    	
    }

    @FXML
    void exportCurrentTab(ActionEvent event) 
    {
    	//exports currently opened tab 
    	DirectoryChooser directoryChooser = new DirectoryChooser();
    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    	File selectedDirectory = directoryChooser.showDialog((stage));
    	
    	
    }

    @FXML
    void importSheet(ActionEvent event) 
    {
    	TableView<inventoryItem> newTable = currentInvTable;
    	FileChooser fileChooser = new FileChooser();
    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    	File selectedFile = fileChooser.showOpenDialog(stage);
    	
    	ObservableList<inventoryItem> data =  CSVReader.read(selectedFile.getAbsolutePath());
    	String tabname = ("Table" + tableTabContainer.getTabs().size());
    	Tab newTab = new Tab(tabname, newTable);
    	tableTabContainer.getTabs().add(newTab);
    	
    	TableColumn<inventoryItem, String> itemNameCol = new TableColumn<inventoryItem, String>("Inventory Item");
    	itemNameCol.setMinWidth(200);
    	itemNameCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, String>("item"));
    	
    	TableColumn<inventoryItem, Integer> itemNumberCol = new TableColumn<inventoryItem, Integer>("ID");
    	itemNumberCol.setMinWidth(50);
    	itemNumberCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, Integer>("productID"));
    	
    	TableColumn<inventoryItem, Integer> itemStockCol = new TableColumn<inventoryItem, Integer>("Current Stock");
    	itemStockCol.setMinWidth(100);
    	itemStockCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, Integer>("quantity"));

    	TableColumn<inventoryItem, String> itemUnitCol = new TableColumn<inventoryItem, String>("Unit");
    	itemUnitCol.setMinWidth(50);
    	itemUnitCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, String>("unit"));
    	
    	TableColumn<inventoryItem, String> itemOrderUnitCol = new TableColumn<inventoryItem, String>("OrderUnit");
    	itemOrderUnitCol.setMinWidth(50);
    	itemOrderUnitCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, String>("orderunit"));
    	
    	TableColumn<inventoryItem, Integer> itemVendorCol = new TableColumn<inventoryItem, Integer>("Vendor");
    	itemVendorCol.setMinWidth(50);
    	itemVendorCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, Integer>("distributor"));
    	
    	newTable.setItems(data);
    	newTable.getColumns().addAll(itemNumberCol, itemNameCol, itemStockCol, itemUnitCol,itemOrderUnitCol, itemVendorCol);
    	newTab.setClosable(true);
    	
    }

    @FXML
    void saveCurrentInv(ActionEvent event) 
    {
    	//this is a database 
    }

    @FXML
    void selectDate(ActionEvent event) 
    {
    	//gets selected date 
    	getDate.getDayCellFactory();
    	
    }
    
    @FXML
    void selectMenuItemManage(ActionEvent event) 
    {
    	//switches to database management
    	try {
    		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/manage.fxml"));
			root = loader.load();
			loader.setController("manageController");
            manageStage.setTitle("Denunzio's Inventory Management Tools");
            manageStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
            manageStage.setScene(new Scene(root, 900, 600));
            manageStage.setResizable(false);
            manageStage.show();
            Stage stage = (Stage) HBoxParent.getScene().getWindow();
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
            Stage stage = (Stage) HBoxParent.getScene().getWindow();
            stage.hide();
        }
        catch (IOException e) 
		{
            e.printStackTrace();
		}
    	
    }

    @FXML
    public void initialize() 
    {
    	ObservableList<inventoryItem> data = DBController.getInventory();

    	TableColumn<inventoryItem, String> itemNameCol = new TableColumn<inventoryItem, String>("Inventory Item");
    	itemNameCol.setMinWidth(200);
    	itemNameCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, String>("item"));
    	
    	TableColumn<inventoryItem, Integer> itemNumberCol = new TableColumn<inventoryItem, Integer>("ID");
    	itemNumberCol.setMinWidth(50);
    	itemNumberCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, Integer>("productID"));
    	
    	TableColumn<inventoryItem, Integer> itemStockCol = new TableColumn<inventoryItem, Integer>("Current Stock");
    	itemStockCol.setMinWidth(100);
    	itemStockCol.setEditable(true);
    	itemStockCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, Integer>("quantity"));
    	itemStockCol.setCellFactory(TextFieldTableCell.<inventoryItem, Integer>forTableColumn(new IntegerStringConverter()));
    	itemStockCol.setOnEditCommit((CellEditEvent<inventoryItem, Integer> event) -> 
    	{
    		int newStock = event.getNewValue();
    		int id = event.getRowValue().productIDProperty().get();
    		DBController.updateStock(id, newStock);
    		
        });
    	TableColumn<inventoryItem, String> itemUnitCol = new TableColumn<inventoryItem, String>("Unit");
    	itemUnitCol.setMinWidth(50);
    	itemUnitCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, String>("unit"));
    	
    	TableColumn<inventoryItem, String> itemOrderUnitCol = new TableColumn<inventoryItem, String>("OrderUnit");
    	itemOrderUnitCol.setMinWidth(50);
    	itemOrderUnitCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, String>("orderunit"));
    	
    	TableColumn<inventoryItem, Integer> itemVendorCol = new TableColumn<inventoryItem, Integer>("Vendor");
    	itemVendorCol.setMinWidth(50);
    	itemVendorCol.setCellValueFactory(new PropertyValueFactory<inventoryItem, Integer>("distributor"));
    	
    	currentInvTable.setItems(data);
    	currentInvTable.getColumns().addAll(itemNumberCol, itemNameCol, itemStockCol, itemUnitCol,itemOrderUnitCol, itemVendorCol);
    	
    }
    

}

