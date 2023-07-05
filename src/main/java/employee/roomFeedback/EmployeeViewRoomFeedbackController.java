package main.java.employee.roomFeedback;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import main.java.feedback.RoomFeedback;


public class EmployeeViewRoomFeedbackController implements Initializable{

	private List<RoomFeedback> roomFeedbacks = new ArrayList<>();
	
    public EmployeeViewRoomFeedbackController(List<RoomFeedback> roomFeedbacks) {
		super();
		this.roomFeedbacks = roomFeedbacks;
	}

    @FXML
    protected StackPane contentArea;
    
    @FXML
    private Label totalFeedbackTitle;
    
	@FXML
    private Label totalDayFeedback;

    @FXML
    private Text searchValidNoti;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<RoomFeedback> feedbackTable;
    
    @SuppressWarnings("unchecked")
	private void showFeedbackByDay(List<RoomFeedback> roomFeedbacks) {
    	
    	TableColumn<RoomFeedback, Integer> feedbackIdColumn = new TableColumn<>("Feedback ID");
		feedbackIdColumn.setCellValueFactory(new PropertyValueFactory<>("feedbackId"));

    	TableColumn<RoomFeedback, Integer> roomIdColumn = new TableColumn<>("Room ID");
    	roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
    	roomIdColumn.setPrefWidth(100);

    	TableColumn<RoomFeedback, Integer> memberIdColumn = new TableColumn<>("Member ID");
    	memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));
    	memberIdColumn.setPrefWidth(100);

    	TableColumn<RoomFeedback, String> contentColumn = new TableColumn<>("Content");
    	contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
    	contentColumn.setPrefWidth(530);
		
    	TableColumn<RoomFeedback, String> timeCreatedColumn = new TableColumn<>("Created Time");
    	timeCreatedColumn.setCellValueFactory(new PropertyValueFactory<>("timeCreated"));
    	timeCreatedColumn.setPrefWidth(250);
    	
    	feedbackTable.getColumns().clear();
    	
    	feedbackTable.getColumns().addAll(feedbackIdColumn, roomIdColumn, memberIdColumn, contentColumn, timeCreatedColumn);
    	
    	ObservableList<RoomFeedback> data = FXCollections.observableArrayList(roomFeedbacks);
    	
    	feedbackTable.setItems(data);
    }

	@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
    	
		showFeedbackByDay(this.roomFeedbacks);
		
		totalFeedbackTitle.setText("Total feedbacks: ");
		
		totalDayFeedback.setText(this.roomFeedbacks.size() + "");
		
    	datePicker.setOnAction(event -> {
    		  
              LocalDate selectedDate = datePicker.getValue();
              
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
              
              String formattedDate = selectedDate.format(formatter);
              
              List<RoomFeedback> filteredFeedbacks = this.roomFeedbacks.stream()
                      .filter(feedback -> feedback.getTimeCreated().contains(formattedDate))
                      .collect(Collectors.toList());
              
      		showFeedbackByDay(filteredFeedbacks);
      		
    		totalFeedbackTitle.setText("Total selected day's feedbacks: ");
      		
      		totalDayFeedback.setText(filteredFeedbacks.size() + "");
        });
	}
}
