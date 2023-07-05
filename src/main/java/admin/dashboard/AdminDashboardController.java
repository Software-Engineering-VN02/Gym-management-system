package main.java.admin.dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import database.GymSystemDB;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import main.java.admin.employees.AdminViewEmployeesController;
import main.java.admin.members.AdminViewMembersController;
import main.java.admin.rooms.AdminViewRoomsController;
import main.java.employee.Employee;
import main.java.login.LoginController;
import main.java.room.Room;

public class AdminDashboardController implements Initializable {
	
	   @FXML
	    private Button membersTab;

	    @FXML
	    private StackPane contentArea;

	    @FXML
	    private StackPane allContentArea;

	    @FXML
	    private Button roomsTab;

	    @FXML
	    private Button logoutButton;

	    @FXML
	    private ImageView accountIcon;
	    
	    @FXML
	    private Button employeesTab;
	    
	    GymSystemDB gymSystemDB = GymSystemDB.GetInstance();
	    
		private void loadAfterNavigate(FXMLLoader loader) {
		  	
			try {
					
				Parent fxml = loader.load();
					
				contentArea.getChildren().removeAll();
					
				contentArea.getChildren().setAll(fxml);
					
			} catch (IOException e1) {
					
				e1.printStackTrace();
				
				}
		  }
	    
		private void loadAfterLogout(FXMLLoader loader) {
			
			try {
				
				Parent fxml = loader.load();
					
				allContentArea.getChildren().removeAll();
				allContentArea.getChildren().setAll(fxml);
					
			} catch (IOException e1) {
					
				e1.printStackTrace();
				
				}
		  }
		
	    private void renderRoomsTab() {
	    	
	    	FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/MemberViewRoomsScreen.fxml"));   
			
			List<Room> rooms = gymSystemDB.getAllRooms();
			
		    AdminViewRoomsController adminViewRoomsController = new AdminViewRoomsController(rooms);
				
		    loader1.setController(adminViewRoomsController);
				
			loadAfterNavigate(loader1);
	    }
	    
	    private void renderMembersTab() {
	    	
	    	FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/EmployeeViewMembersScreen.fxml"));   
						
			AdminViewMembersController adminViewMembersController = new AdminViewMembersController();
				
		    loader1.setController(adminViewMembersController);
				
			loadAfterNavigate(loader1);
	    }

	    private void renderEmployeesTab() {
	    	
	    	FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/MemberViewEmployeesScreen.fxml"));   
	    	
	    	List<Employee> employees = gymSystemDB.getEmployeesForAdmin();
			
			AdminViewEmployeesController adminViewEmployeesController = new AdminViewEmployeesController(employees);
				
		    loader1.setController(adminViewEmployeesController);
				
			loadAfterNavigate(loader1);
	    }
	    
		private void logoutHandler() {
			
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/LoginScreen.fxml"));   
					
		    LoginController loginController = new LoginController();
				
		    loader1.setController(loginController);
				
		    loadAfterLogout(loader1);
		}
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			renderRoomsTab();
			
			roomsTab.setOnMouseClicked(e -> {
				
				renderRoomsTab();
			});
			
			membersTab.setOnMouseClicked(e -> {
				
				renderMembersTab();
			});
			
			employeesTab.setOnMouseClicked(e -> {
				
				renderEmployeesTab();
			});
			
			logoutButton.setOnMouseClicked(e -> {
				
				logoutHandler();
			});
		}
		
		
}
