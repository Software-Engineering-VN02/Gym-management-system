package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		FXMLLoader loader = new FXMLLoader();
		
		try {
			
		loader.setLocation(getClass().getResource("/main/java/screen/AppScreen.fxml"));
		
		AppScreenController appScreenController = new AppScreenController();
		
		loader.setController(appScreenController);
			
		Parent root = loader.load();
		
		primaryStage.initStyle(StageStyle.UNDECORATED);
		
		primaryStage.setScene(new Scene(root, 1080, 700));
		
		primaryStage.show();
		
		} catch (IOException e) {
			
			e.printStackTrace();
	}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
