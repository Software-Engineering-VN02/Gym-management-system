package main.java.admin.rooms;

import java.io.IOException;
import java.util.List;

import database.GymSystemDB;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import main.java.admin.room.AdminViewRoomController;
import main.java.admin.roomFeedback.AdminViewRoomFeedbackController;
import main.java.course.Course;
import main.java.employee.rooms.EmployeeViewRoomsController;
import main.java.equipment.Equipment;
import main.java.feedback.RoomFeedback;
import main.java.room.Room;

public class AdminViewRoomsController extends EmployeeViewRoomsController{

	public AdminViewRoomsController(List<Room> rooms) {
		super(rooms);
	}
	
	GymSystemDB gymSystemDB = GymSystemDB.GetInstance();
	
	@Override
	protected void seeRoomDetail(Room room, List<Course> roomCourses, List<Equipment> equipments) {
		
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/MemberViewRoomScreen.fxml"));   
				
		AdminViewRoomController adminViewRoomController = new AdminViewRoomController(room, roomCourses, equipments);
			
	    loader1.setController(adminViewRoomController);
	    
	    try {
			
			Parent fxml = loader1.load();
				
			allContentArea.getChildren().removeAll();
				
			allContentArea.getChildren().setAll(fxml);
				
		} catch (IOException e1) {
				
			e1.printStackTrace();
			
		}
			
	}
	
	private void loadAfterNavigate(FXMLLoader loader) {
	  	
		try {
				
			Parent fxml = loader.load();
				
			allContentArea.getChildren().removeAll();
				
			allContentArea.getChildren().setAll(fxml);
				
		} catch (IOException e1) {
				
			e1.printStackTrace();
			
			}
	}
	
	
	@Override
	protected void renderAllRooms() {
		
		super.renderAllRooms();
		
		TableColumn<Room, String> viewFeedbackCol = new TableColumn<Room, String>("Action");
	      
	    viewFeedbackCol.setCellValueFactory(new PropertyValueFactory<>("address"));
	    
	     Callback<TableColumn<Room, String>, TableCell<Room, String>> cellFactory = new Callback<TableColumn<Room, String>, TableCell<Room, String>>() {
	    	 
	            @Override
	            public TableCell<Room, String> call(final TableColumn<Room, String> param) {
	            	
	                final TableCell<Room, String> cell = new TableCell<Room, String>() {

	                    final Button btn = new Button("See feedback");
	                    	                    
	                    @Override
	                    public void updateItem(String item, boolean empty) {
	                        super.updateItem(item, empty);
	                        
	                        if (empty) {
	                        	
	                            setGraphic(null);
	                            setText(null);
	                        } else {
	                        	
	                            btn.setOnAction(event -> {
	                            	
	                            	Room room = getTableView().getItems().get(getIndex());
	                            	
	                            	int id = room.getRoomId();
	                            		                            	
	                            	FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/EmployeeViewRoomFeedbackScreen.fxml"));
	                            	
	                            	List<RoomFeedback> roomFeedbacks = gymSystemDB.getRoomFeedbacks(id + "");
	                            	
	                            	AdminViewRoomFeedbackController adminViewRoomFeedbackController = new AdminViewRoomFeedbackController(roomFeedbacks);
	                        			
	                        	    loader1.setController(adminViewRoomFeedbackController);
	                        			
	                        	    loadAfterNavigate(loader1);
	                            	
	                            });
	                            setGraphic(btn);
	                            setText(null);
	                        }
	                    }
	                };
	                return cell;
	            }
	        };

	      viewFeedbackCol.setCellFactory(cellFactory);
	      
	      roomsTable.getColumns().add(viewFeedbackCol);
	}
}
