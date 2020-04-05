package application;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class main extends Application
{
	
	
	public static void main(String[] args) 
	{
	    Application.launch(main.class, args);
	}

	@Override
	public void start(Stage mainStage) throws Exception 
	{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		mainStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
		mainStage.setTitle("Inventory Tools");
		mainStage.setScene(scene);
		mainStage.setResizable(false);
		mainStage.show();
	} 
	


}
