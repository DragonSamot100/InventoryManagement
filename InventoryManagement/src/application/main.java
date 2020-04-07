package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
