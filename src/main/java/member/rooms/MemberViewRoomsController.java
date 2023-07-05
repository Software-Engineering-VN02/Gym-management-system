package main.java.member.rooms;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import database.GymSystemDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import main.java.course.Course;
import main.java.equipment.Equipment;
import main.java.member.room.MemberViewRoomController;
import main.java.room.Room;

public class MemberViewRoomsController implements Initializable{

	private List<Room> rooms = new ArrayList<>();
	
    public MemberViewRoomsController(List<Room> rooms) {
		super();
		this.rooms = rooms;
	}

	@FXML
    protected TableView<Room> roomsTable;
	
    @FXML
    private Label totalRoomsNumber;
    
    @FXML
    private TextField searchField;

    @FXML
    private Button searchBtn;
    
    @FXML
    private Text searchValidNoti;
    
    @FXML
    protected StackPane allContentArea;


    @SuppressWarnings("unchecked")
	protected void renderAllRooms() {
    	
    	TableColumn<Room, Integer> roomIdColumn = new TableColumn<>("Room Id");
		
	    TableColumn<Room, String> roomNameColumn = new TableColumn<>("Room Name");

	    TableColumn<Room, Integer> employeeInChargeIdColumn = new TableColumn<>("Employee In Charge");

	    roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
	    
	    roomIdColumn.setPrefWidth(230);
	    
	    roomNameColumn.setCellValueFactory(new PropertyValueFactory<>("roomName"));
	    
	    roomNameColumn.setPrefWidth(500);
	    
	    employeeInChargeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeInChargeId"));
	    
	    employeeInChargeIdColumn.setPrefWidth(350);
	    
		roomsTable.getColumns().clear();
	    
	    roomsTable.getColumns().addAll(roomIdColumn, roomNameColumn, employeeInChargeIdColumn);

	    ObservableList<Room> data = FXCollections.observableArrayList(this.rooms);
	    
	    roomsTable.setItems(data);
	    
	    totalRoomsNumber.setText(rooms.size() + "");
    }
    
	protected void seeRoomDetail(Room room, List<Course> roomCourses, List<Equipment> equipments) {
		
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/MemberViewRoomScreen.fxml"));   
				
	    MemberViewRoomController memberViewRoomController = new MemberViewRoomController(room, roomCourses, equipments);
			
	    loader1.setController(memberViewRoomController);
	    
	    try {
			
			Parent fxml = loader1.load();
				
			allContentArea.getChildren().removeAll();
				
			allContentArea.getChildren().setAll(fxml);
				
		} catch (IOException e1) {
				
			e1.printStackTrace();
			
		}
			
	}
    
    GymSystemDB gymSystemDB = GymSystemDB.GetInstance();

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			
		renderAllRooms();
		
		searchBtn.setOnMouseClicked(e -> {
			
			String searchValue = searchField.getText();
			
			if (searchValue.length() == 0) {
				
				renderAllRooms();
				
			} else {
				
				Room foundRoom = gymSystemDB.getRoomInfomation(searchValue);
				
				if (foundRoom == null) {
					
					searchValidNoti.setText("Not found room with this room id");
					
				} else {
					
					TableColumn<Room, Integer> roomIdColumn = new TableColumn<>("Room Id");
					roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));

					TableColumn<Room, String> roomNameColumn = new TableColumn<>("Room Name");
					roomNameColumn.setCellValueFactory(new PropertyValueFactory<>("roomName"));

					TableColumn<Room, Integer> roomEmployeeInChargeIdColumn = new TableColumn<>("In Charge");
					roomEmployeeInChargeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeInChargeId"));
					
					TableColumn<Room, Integer> totalEmployeeColumn = new TableColumn<>("Employee number");
					totalEmployeeColumn.setCellValueFactory(new PropertyValueFactory<>("employeeCount"));
					
					totalEmployeeColumn.setPrefWidth(70);
					
					TableColumn<Room, String> employeeListColumn = new TableColumn<>("Employees");
					employeeListColumn.setCellValueFactory(new PropertyValueFactory<>("employeeList"));
					
					employeeListColumn.setPrefWidth(200);
					
					TableColumn<Room, String> equipmentListColumn = new TableColumn<>("Equipments");
					equipmentListColumn.setCellValueFactory(new PropertyValueFactory<>("equipmentList"));
					
					equipmentListColumn.setPrefWidth(300);
					
					TableColumn<Room, String> courseListColumnb = new TableColumn<>("Courses");
					courseListColumnb.setCellValueFactory(new PropertyValueFactory<>("courseList"));
					
					courseListColumnb.setPrefWidth(280);
					
					roomsTable.getColumns().clear();
					
					roomsTable.getColumns().addAll(roomIdColumn, roomNameColumn, roomEmployeeInChargeIdColumn, totalEmployeeColumn, employeeListColumn, equipmentListColumn, courseListColumnb);
					
					List<Room> rooms = new ArrayList<>();
					
					rooms.add(foundRoom);
					
					ObservableList<Room> data = FXCollections.observableArrayList(rooms);
					
					roomsTable.setItems(data);
				}
			}
			
		});
		
		roomsTable.setRowFactory( tv -> {
			
		    TableRow<Room> row = new TableRow<>();
		    
		    row.setOnMouseClicked(event -> {
		    	
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		        	
		        	Room rowData = row.getItem();
		        	
		        	Room roomDetail = gymSystemDB.getRoomInfomation(rowData.getRoomId() + "");
		        			        	
		        	List<Course> roomCourses = gymSystemDB.getRoomCourses(rowData.getRoomId() + "");
		        	
		        	List<Equipment> roomEquipments = gymSystemDB.getEquipmentsByRoomId(rowData.getRoomId() + "");
		        			        	
		        	seeRoomDetail(roomDetail, roomCourses, roomEquipments);
		        	
		        }
		    });
		    return row ;
		});
	}
}
