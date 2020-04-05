package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class menuController 
{
	Parent root;
	Stage manageStage = new Stage();
	
    @FXML
    private VBox vBoxMenuFrame;

    @FXML
    private Button addBox;

    @FXML
    private ChoiceBox<?> sortBox;

    @FXML
    private Button deleteItemButton;

    @FXML
    private Button modifyButton;

    @FXML
    private MenuButton menuSelector;

    @FXML
    private MenuItem menuItemManage;

    @FXML
    private MenuItem MenuItemMenu;

    @FXML
    private MenuItem menuItemPARS;

    @FXML
    private MenuItem menuItemView;

    @FXML
    void addMenuItem(ActionEvent event) 
    {
    	//prompts user to add a new menu item to the database?
    }

    @FXML
    void deleteMenuItem(ActionEvent event) 
    {
    	//removes menu item from database?
    }

    @FXML
    void menuItemSelected(ActionEvent event) 
    {
    	//supposed to close this window
    }

    @FXML
    void modifyMenuItem(ActionEvent event) 
    {
    	//modifies database? entry for a menu item
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
            Stage stage = (Stage) vBoxMenuFrame.getScene().getWindow();
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
            Stage stage = (Stage) vBoxMenuFrame.getScene().getWindow();
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
            Stage stage = (Stage) vBoxMenuFrame.getScene().getWindow();
            stage.hide();
        }
        catch (IOException e) 
		{
            e.printStackTrace();
		}
    	
    }

}
