package main.java.member.room;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import main.java.course.Course;
import main.java.equipment.Equipment;
import main.java.feedback.CourseFeedback;
import main.java.member.courseFeedback.MemberViewCourseFeedbackController;
import main.java.member.equipments.MemberViewEquipmentsController;
import main.java.room.Room;

public class MemberViewRoomController implements Initializable{
	
	 private Room room;
	 
	 private List<Course> roomCourses;
	 
	 private List<Equipment> roomEquipments;
	 
	public MemberViewRoomController(Room room, List<Course> roomCourses, List<Equipment> roomEquipments) {
		super();
		this.room = room;
		this.roomCourses = roomCourses;
		this.roomEquipments = roomEquipments;
	}

	public List<Course> getRoomCourses() {
		return roomCourses;
	}

	public void setRoomCourses(List<Course> roomCourses) {
		this.roomCourses = roomCourses;
	}

	public List<Equipment> getRoomEquipments() {
		return roomEquipments;
	}

	public void setRoomEquipments(List<Equipment> roomEquipments) {
		this.roomEquipments = roomEquipments;
	}

	@FXML
	  private Label equipments;

	  @FXML
	  private Label employeeInChargeId;

      @FXML
	  protected TableView<Course> coursesTable;

	  @FXML
	  private Label employees;

	  @FXML
	  private Label roomId;

	  @FXML
	  private Label roomName;
	  
	  @FXML
	  private StackPane contentArea;
	  
	  @FXML
	  protected Button seeEquipmentsBtn;
	  
	  GymSystemDB gymSystemDB = GymSystemDB.GetInstance();

	  protected void renderRoomInfomation() {
			 
			 roomId.setText(this.room.getRoomId() + "");
			 roomName.setText(this.room.getRoomName());
			 employeeInChargeId.setText(this.room.getEmployeeInChargeId() + "");
			 employees.setText(this.room.getEmployeeList());
			 equipments.setText(this.room.getEquipmentList());
     }
	  
	  @SuppressWarnings("unchecked")
	  protected void renderMyRoomCourses() {
			 
		  TableColumn<Course, Integer> courseIdColumn = new TableColumn<>("Course Id");
		 
	      TableColumn<Course, String> courseNameColumn = new TableColumn<>("Course name");
	      
	      courseNameColumn.setPrefWidth(200);
		 
	      TableColumn<Course, String> courseBeginColumn = new TableColumn<>("Course begin");
	      
	      courseBeginColumn.setPrefWidth(150);
		 		 
	      TableColumn<Course, Integer> coachIdColumn = new TableColumn<>("Coach Id");
	      
	      TableColumn<Course, Integer> coachNameColumn = new TableColumn<>("Coach name");
	      
	      coachNameColumn.setPrefWidth(200);
		 
	      TableColumn<Course, Integer> priceColumn = new TableColumn<>("Price");
		
	      TableColumn<Course, String> courseTypeColumn = new TableColumn<>("Course type");
	      
	      courseTypeColumn.setPrefWidth(200);
	      
	      courseIdColumn.setCellValueFactory(new PropertyValueFactory<>("courseId"));
	      
	      courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
	      
	      courseBeginColumn.setCellValueFactory(new PropertyValueFactory<>("courseBegin"));
	      	      
	      coachIdColumn.setCellValueFactory(new PropertyValueFactory<>("coachId"));
	      
	      coachNameColumn.setCellValueFactory(new PropertyValueFactory<>("coachName"));
	      
	      priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
	      
	      courseTypeColumn.setCellValueFactory(new PropertyValueFactory<>("courseType"));
	      
	     @SuppressWarnings("rawtypes")
		 TableColumn viewFeedbackCol = new TableColumn("Action");
	      
	      viewFeedbackCol.setCellValueFactory(new PropertyValueFactory<>("courseType"));

	     Callback<TableColumn<Course, String>, TableCell<Course, String>> cellFactory = new Callback<TableColumn<Course, String>, TableCell<Course, String>>() {
	    	 
	            @Override
	            public TableCell<Course, String> call(final TableColumn<Course, String> param) {
	            	
	                final TableCell<Course, String> cell = new TableCell<Course, String>() {

	                    final Button btn = new Button("See feedback");
	                    	                    
	                    @Override
	                    public void updateItem(String item, boolean empty) {
	                        super.updateItem(item, empty);
	                        
	                        if (empty) {
	                        	
	                            setGraphic(null);
	                            setText(null);
	                        } else {
	                        	
	                            btn.setOnAction(event -> {
	                            	
	                            	Course course = getTableView().getItems().get(getIndex());
	                            	
	                            	int id = course.getCourseId();
	                            		                            	
	                            	FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/MemberViewCourseFeedbackScreen.fxml"));
	                            	
	                            	List<CourseFeedback> courseFeedbacks = gymSystemDB.getCourseFeedbacks(id + "");
	                            	
	                                MemberViewCourseFeedbackController memberViewCourseFeedbackController = new MemberViewCourseFeedbackController(courseFeedbacks);
	                        			
	                        	    loader1.setController(memberViewCourseFeedbackController);
	                        			
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
	      
	      coursesTable.getColumns().clear();
			
	      coursesTable.getColumns().addAll(courseIdColumn, courseNameColumn, courseBeginColumn, coachIdColumn, coachNameColumn, priceColumn, courseTypeColumn, viewFeedbackCol);
		 
	      ObservableList<Course> courseData = FXCollections.observableArrayList(this.roomCourses);
	      
	      coursesTable.setItems(courseData);

	 }
	  
	  protected void loadAfterNavigate(FXMLLoader loader) {
			  	
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
		
		renderRoomInfomation();
		
		renderMyRoomCourses();
		
		seeEquipmentsBtn.setOnMouseClicked(e -> {
			
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/MemberViewEquipmentsScreen.fxml"));
								
			MemberViewEquipmentsController memberViewEquipmentsController = new MemberViewEquipmentsController(this.roomEquipments);
				
		    loader1.setController(memberViewEquipmentsController);
				
		    loadAfterNavigate(loader1);
		});
	}
}
