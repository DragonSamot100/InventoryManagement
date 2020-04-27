package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class mainController 
{
	Stage manageStage = new Stage();
	Parent root;
	@FXML
    private VBox homePage;

    @FXML
    private GridPane homeGrid;

    @FXML
    private Button selectViewInv;

    @FXML
    private Button selectManageInv;

    @FXML
    private Button selectManageMenu;
    		
    @FXML
    private Button editLogin;
	
    @FXML
    private HBox HBoxShell;

    @FXML
    private GridPane gridMenu;
    
    @FXML
    private TabPane tabPaneView;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField; 
    
    @FXML
    void login(ActionEvent event) 
    {
    	String username = usernameField.getText();
    	String password = passwordField.getText();
    	
    	if(usernameField.getText().equals("user") && passwordField.getText().equals("password"))
    	{
    		try 
    		{
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
				root = loader.load();
	            manageStage.setTitle("Inventory Tools");
	            manageStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
	            manageStage.setScene(new Scene(root));
	            manageStage.setResizable(false);
	            manageStage.show();
	            ((Node)(event.getSource())).getScene().getWindow().hide();
	        }
	        catch (IOException e) 
			{
	            e.printStackTrace();
			}
    	}
    	else
    	{
    		
    	}
    }
	@FXML
	void handleViewbutton(ActionEvent event) 
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
	            ((Node)(event.getSource())).getScene().getWindow().hide();
	        }
	        catch (IOException e) 
			{
	            e.printStackTrace();
			}
			
	    }

	@FXML
	void handleMenubutton(ActionEvent event) 
	{
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/menuManage.fxml"));
			root = loader.load();
			loader.setController("menuController");
            manageStage.setTitle("Denunzio's Inventory Management Tools");
            manageStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
            manageStage.setScene(new Scene(root, 900, 600));
            manageStage.setResizable(false);
            manageStage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) 
		{
            e.printStackTrace();
		}

	}

	@FXML
	void handlePARSbutton(ActionEvent event) 
	{
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/parsSettings.fxml"));
			root = loader.load();
			loader.setController("parsController");
            manageStage.setTitle("Denunzio's Inventory Management Tools");
            manageStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
            manageStage.setScene(new Scene(root, 900, 600));
            manageStage.setResizable(false);
            manageStage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) 
		{
            e.printStackTrace();
		}
	}

	@FXML
	void handleManagebutton(ActionEvent event) 
	{
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/manage.fxml"));
			root = loader.load();
			loader.setController("manageController");
            manageStage.setTitle("Denunzio's Inventory Management Tools");
            manageStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
            manageStage.setScene(new Scene(root, 900, 600));
            manageStage.setResizable(false);
            manageStage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) 
		{
            e.printStackTrace();
		}
	}
	@FXML
	void editCredentials(ActionEvent event) 
	{
		
	}
	    
}
