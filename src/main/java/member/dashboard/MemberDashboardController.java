package main.java.member.dashboard;

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
import main.java.login.LoginController;
import main.java.member.Member;
import main.java.member.employees.view.MemberViewEmployeesController;
import main.java.member.feedback.SendFeedbackController;
import main.java.member.myCourses.MemberViewMyCoursesController;
import main.java.member.profile.MemberViewProfile;
import main.java.member.rooms.MemberViewRoomsController;
import main.java.member.usageHistory.MemberViewUsageHistoryController;
import main.java.room.Room;
import main.java.service.Service;

public class MemberDashboardController implements Initializable{
	
	GymSystemDB gymSystemDB = GymSystemDB.GetInstance();
	
    @FXML
    private StackPane contentArea;

    @FXML
    private StackPane allContentArea;

    @FXML
    private Button roomsTab;

    @FXML
    private Button coachesTab;

    @FXML
    private Button myCoursesTab;

    @FXML
    private Button feedbackTab;

    @FXML
    private Button logoutButton;

    @FXML
    private ImageView accountTab;
    
    @FXML
    private Button historyTab;
    
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
		
	    MemberViewRoomsController memberViewRoomsController = new MemberViewRoomsController(rooms);
			
	    loader1.setController(memberViewRoomsController);
			
		loadAfterNavigate(loader1);
	}
	
	private void renderMyCourses() {
		
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/MemberViewMyCoursesScreen.fxml")); 
		
		List<Course> myCourses = gymSystemDB.getMyCourses("2");
				
	    MemberViewMyCoursesController memberViewMyCoursesController = new MemberViewMyCoursesController(myCourses);
			
	    loader1.setController(memberViewMyCoursesController);
			
		loadAfterNavigate(loader1);
	}
	
	private void renderCoaches() {
		
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/MemberViewEmployeesScreen.fxml"));
		
		List<Employee> employees = gymSystemDB.getEmployeesForMember();
						
	    MemberViewEmployeesController memberViewEmployeesController = new MemberViewEmployeesController(employees);
			
	    loader1.setController(memberViewEmployeesController);
			
		loadAfterNavigate(loader1);
	}
	
	private void renderFeedbackForm() {

		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/SendFeedbackScreen.fxml"));
								
	    SendFeedbackController sendFeedbackController = new SendFeedbackController();
			
	    loader1.setController(sendFeedbackController);
			
		loadAfterNavigate(loader1);
	}
	
	private void logoutHandler() {
		
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/LoginScreen.fxml"));
		
		LoginController loginController = new LoginController();
			
	    loader1.setController(loginController);
			
	    loadAfterLogout(loader1);
		
	}
	
	private void seeProfileHandler() {
		
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/ProfileScreen.fxml"));
		
		Member member = gymSystemDB.getMemberByMemberId("1");
		
		MemberViewProfile memberViewProfile = new MemberViewProfile(member);
			
	    loader1.setController(memberViewProfile);
			
	    loadAfterNavigate(loader1);
	}
	
	private void seeUsageHistoryHandler() {
		
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/MemberViewUsageHistoryScreen.fxml"));
		
		List<Service> services = gymSystemDB.getMemberUsageHistory("1");
				
		MemberViewUsageHistoryController memberViewUsageHistoryController = new MemberViewUsageHistoryController(services);
			
	    loader1.setController(memberViewUsageHistoryController);
			
	    loadAfterNavigate(loader1);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		renderRoomsTab();
		
		roomsTab.setOnMouseClicked(e -> {
			
			renderRoomsTab();
		});
		
		
		myCoursesTab.setOnMouseClicked(e -> {
			
			renderMyCourses();
		});
		
		coachesTab.setOnMouseClicked(e -> {
			
			renderCoaches();
		});
		
		feedbackTab.setOnMouseClicked(e -> {
			
			renderFeedbackForm();
		});
		
		logoutButton.setOnMouseClicked(e -> {
			
			logoutHandler();
		});
		
		accountTab.setOnMouseClicked(e -> {
			
			seeProfileHandler();
		});
		
		historyTab.setOnMouseClicked(e -> {
			
			seeUsageHistoryHandler();
		});
	}

}
