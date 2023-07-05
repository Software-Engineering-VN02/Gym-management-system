package main.java.employee.rooms;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import main.java.course.Course;
import main.java.employee.room.EmployeeViewRoomController;
import main.java.equipment.Equipment;
import main.java.member.rooms.MemberViewRoomsController;
import main.java.room.Room;

public class EmployeeViewRoomsController extends MemberViewRoomsController{

	public EmployeeViewRoomsController(List<Room> rooms) {
		super(rooms);
	}
	
	@Override
	protected void seeRoomDetail(Room room, List<Course> roomCourses, List<Equipment> equipments) {
		
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/MemberViewRoomScreen.fxml"));   
				
		EmployeeViewRoomController employeeViewRoomController = new EmployeeViewRoomController(room, roomCourses, equipments);
			
	    loader1.setController(employeeViewRoomController);
	    
	    try {
			
			Parent fxml = loader1.load();
				
			allContentArea.getChildren().removeAll();
				
			allContentArea.getChildren().setAll(fxml);
				
		} catch (IOException e1) {
				
			e1.printStackTrace();
			
		}
			
	}
}
