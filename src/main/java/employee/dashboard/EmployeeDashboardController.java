package main.java.employee.dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import main.java.employee.rooms.EmployeeViewRoomsController;

public class EmployeeDashboardController implements Initializable{
	
	@FXML
	private Button myMembersTab;

	@FXML
	private StackPane contentArea;

	@FXML
	private StackPane allContentArea;

	@FXML
	private Button roomsTab;

	@FXML
	private Button myRoomTab;

	@FXML
	private Button logoutButton;

	@FXML
	private ImageView notificationIcon;
	
	private void loadAfterNavigate(FXMLLoader loader) {
	  	
		try {
				
			Parent fxml = loader.load();
				
			contentArea.getChildren().removeAll();
				
			contentArea.getChildren().setAll(fxml);
				
		} catch (IOException e1) {
				
			e1.printStackTrace();
			
			}
	   }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		roomsTab.setOnMouseClicked(e->{
						
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/EmployeeViewRoomsScreen.fxml"));    	
			
		    EmployeeViewRoomsController employeeViewRoomsController = new EmployeeViewRoomsController();
				
		    loader1.setController(employeeViewRoomsController);
				
			loadAfterNavigate(loader1);
			
		});
	}

}
