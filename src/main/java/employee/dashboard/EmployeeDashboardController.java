package main.java.employee.dashboard;

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
import main.java.course.Course;
import main.java.employee.Employee;
import main.java.employee.members.EmployeeViewMembers;
import main.java.employee.myProfile.EmployeeViewMyProfileController;
import main.java.employee.room.EmployeeViewRoomController;
import main.java.employee.roomFeedback.EmployeeViewRoomFeedbackController;
import main.java.employee.rooms.EmployeeViewRoomsController;
import main.java.equipment.Equipment;
import main.java.feedback.RoomFeedback;
//import main.java.equipment.Equipment;
import main.java.login.LoginController;
import main.java.room.Room;

public class EmployeeDashboardController implements Initializable{
	
	@FXML
	private Button membersTab;

	@FXML
	private StackPane contentArea;

	@FXML
	private StackPane allContentArea;

	@FXML
	private Button roomsTab;
	
	@FXML
	private Button myRoomTab;
	
    @FXML
    private Button feedbackTab;

	@FXML
	private Button logoutButton;

	@FXML
	private ImageView accountIcon;
	
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
		
	    EmployeeViewRoomsController employeeViewRoomsController = new EmployeeViewRoomsController(rooms);
			
	    loader1.setController(employeeViewRoomsController);
			
		loadAfterNavigate(loader1);
	}
	
	private void logoutHandler() {
		
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/LoginScreen.fxml"));   
				
	    LoginController loginController = new LoginController();
			
	    loader1.setController(loginController);
			
	    loadAfterLogout(loader1);
	}
	
	private void showProfile() {
		
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/EmployeeViewProfileScreen.fxml"));   
		
		Employee employee = gymSystemDB.getEmployeeById("1");
		
	    EmployeeViewMyProfileController employeeViewProfileController = new EmployeeViewMyProfileController(employee);
			
	    loader1.setController(employeeViewProfileController);
			
		loadAfterNavigate(loader1);	
	}
	
	GymSystemDB gymSystemDB = GymSystemDB.GetInstance();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		renderRoomsTab();

		roomsTab.setOnMouseClicked(e->{
						
			renderRoomsTab();
		});
		
		myRoomTab.setOnMouseClicked(e->{
			

			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/MemberViewRoomScreen.fxml"));
			
			Room myRoom = gymSystemDB.getRoomInfomation("1");
			
			List<Course> myRoomCourses = gymSystemDB.getRoomCourses("1");
			
			List<Equipment> equipments = gymSystemDB.getEquipmentsByRoomId("1");
									
			EmployeeViewRoomController employeeViewMyRoomController = new EmployeeViewRoomController(myRoom, myRoomCourses,equipments);
				
		    loader1.setController(employeeViewMyRoomController);
				
			loadAfterNavigate(loader1);
		});
		
		membersTab.setOnMouseClicked(e -> {
			
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/EmployeeViewMembersScreen.fxml"));
												
			EmployeeViewMembers employeeViewMembers = new EmployeeViewMembers();
				
		    loader1.setController(employeeViewMembers);
				
			loadAfterNavigate(loader1);
		});
		
		feedbackTab.setOnMouseClicked(e -> {
			
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/EmployeeViewRoomFeedbackScreen.fxml"));
			
			List<RoomFeedback> roomFeedbacks = gymSystemDB.getRoomFeedbacks("1");
			
			EmployeeViewRoomFeedbackController employeeViewRoomFeedbackController = new EmployeeViewRoomFeedbackController(roomFeedbacks);
				
		    loader1.setController(employeeViewRoomFeedbackController);
				
			loadAfterNavigate(loader1);
		});
		
		logoutButton.setOnMouseClicked(e -> {
			
			logoutHandler();
		});
		
		accountIcon.setOnMouseClicked(e -> {
			
			showProfile();
		});
	}
}
