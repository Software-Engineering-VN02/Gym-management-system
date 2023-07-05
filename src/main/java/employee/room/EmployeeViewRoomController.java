package main.java.employee.room;
import main.java.course.Course;
import main.java.employee.Employee;
import main.java.employee.employeeProfile.EmployeeViewEmployeeProfileController;
import main.java.employee.equipments.EmployeeViewEquipmentsController;
import main.java.equipment.Equipment;
import main.java.member.room.MemberViewRoomController;
import main.java.room.Room;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import database.GymSystemDB;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;

public class EmployeeViewRoomController extends MemberViewRoomController implements Initializable{

	public EmployeeViewRoomController(Room room, List<Course> roomCourses, List<Equipment> roomEquipments) {
		super(room, roomCourses, roomEquipments);
		// TODO Auto-generated constructor stub
	}

	private void navigateToEmployeeProfile(Employee employee) {
		
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/EmployeeViewProfileScreen.fxml"));
		
		EmployeeViewEmployeeProfileController employeeViewEmployeeProfileController = new EmployeeViewEmployeeProfileController(employee);
			
	    loader1.setController(employeeViewEmployeeProfileController);
			
	    loadAfterNavigate(loader1);
	}
	
	GymSystemDB gymSystemDB = GymSystemDB.GetInstance();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
				
		renderRoomInfomation();
		
		renderMyRoomCourses();
		
		seeEquipmentsBtn.setOnMouseClicked(e -> {
			
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/MemberViewEquipmentsScreen.fxml"));
								
			EmployeeViewEquipmentsController employeeViewEquipmentsController = new EmployeeViewEquipmentsController(getRoomEquipments());
				
		    loader1.setController(employeeViewEquipmentsController);
				
		    loadAfterNavigate(loader1);
		});
		
		coursesTable.setOnMouseClicked(event -> {
						 
			 if (event.getClickCount() == 2) {
				 
			        @SuppressWarnings("unchecked")
					TablePosition<Course, ?> position = coursesTable.getSelectionModel().getSelectedCells().get(0);
			        
			        int rowIndex = position.getRow();
			        
			        TableColumn<Course, ?> column = position.getTableColumn();
			        
			        Course selectedItem = coursesTable.getItems().get(rowIndex);
			        			        
			        // Perform the desired action based on the column and row index
			        if (column.getText().equals("Coach name")) {
			        	
			            // Get the content of the clicked cell			            
			            
			            TableColumn<Course, ?> coachIdColumn = coursesTable.getColumns().get(3);
			            
			            String coachId = coachIdColumn.getCellData(selectedItem).toString();
			            // Perform further actions with the equipment status
			            
			            Employee employee = gymSystemDB.getEmployeeById(coachId);
			            
			            navigateToEmployeeProfile(employee);
			            			           
			        }      
			    }
		   });
	}
	
	
	
}
