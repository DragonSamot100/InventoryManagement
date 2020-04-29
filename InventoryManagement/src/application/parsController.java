package application;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class parsController 
{
	Parent root;
	Stage manageStage = new Stage();
    @FXML
    private VBox vBoxPARSFrame;

    @FXML
    private Button importButton;

    @FXML
    private Button exportButton;

    @FXML
    private Button saveButton;

    @FXML
    private ChoiceBox<?> recentBox;

    @FXML
    private MenuButton menuSelector;

    @FXML
    private MenuItem menuItemManage;

    @FXML
    private MenuItem menuItemPARS;

    @FXML
    private MenuItem menuItemView;

    @FXML
    private TableColumn<?, ?> nameTable2;

    @FXML
    private TableColumn<?, ?> nameTable;

    @FXML
    private TableColumn<?, ?> nameTable11;

    @FXML
    private TableColumn<?, ?> nameTable111;

    @FXML
    private TableColumn<?, ?> nameTable1;

    @FXML
    private TableColumn<?, ?> nameTable12;

    @FXML
    private TableColumn<?, ?> nameTable121;

    @FXML
    private TableColumn<?, ?> nameTable1211;

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
    	//imports pars sheet?
    	FileChooser fileChooser = new FileChooser();
    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
    	File selectedFile = fileChooser.showOpenDialog(stage);
    }

    @FXML
    void menuItemSelected(ActionEvent event) 
    {
    	//supposed to close window
    }

    @FXML
    void savePARSheet(ActionEvent event) 
    {
    	//saves pars sheet to auto load next opening
    	
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
            Stage stage = (Stage) vBoxPARSFrame.getScene().getWindow();
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
            Stage stage = (Stage) vBoxPARSFrame.getScene().getWindow();
            stage.hide();
        }
        catch (IOException e) 
		{
            e.printStackTrace();
		}
    }

}
