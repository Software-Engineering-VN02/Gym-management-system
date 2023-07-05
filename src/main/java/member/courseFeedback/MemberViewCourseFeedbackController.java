package main.java.member.courseFeedback;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import main.java.feedback.CourseFeedback;

public class MemberViewCourseFeedbackController implements Initializable{

	private List<CourseFeedback> courseFeedbacks = new ArrayList<>();
	
	public MemberViewCourseFeedbackController(List<CourseFeedback> courseFeedbacks) {
		super();
		this.courseFeedbacks = courseFeedbacks;
	}

	@FXML
    private Label totalDayFeedback;

    @FXML
    private Text searchValidNoti;

    @FXML
    private Label totalFeedbackTitle;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<CourseFeedback> feedbackTable;

    @SuppressWarnings("unchecked")
	private void renderCourseFeedbackColumn(List<CourseFeedback> courseFeedbacks) {
    	
    	// Create table columns
        TableColumn<CourseFeedback, Integer> feedbackIdColumn = new TableColumn<>("Feedback ID");
        feedbackIdColumn.setCellValueFactory(new PropertyValueFactory<>("feedbackId"));
        
        TableColumn<CourseFeedback, Integer> memberIdColumn = new TableColumn<>("Member ID");
        memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));

        TableColumn<CourseFeedback, Integer> courseIdColumn = new TableColumn<>("Course ID");
        courseIdColumn.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        
        courseIdColumn.setPrefWidth(100);

        TableColumn<CourseFeedback, String> contentColumn = new TableColumn<>("Content");
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        
        contentColumn.setPrefWidth(450);

        TableColumn<CourseFeedback, String> timeCreatedColumn = new TableColumn<>("Time Created");
        timeCreatedColumn.setCellValueFactory(new PropertyValueFactory<>("timeCreated"));
        
        timeCreatedColumn.setPrefWidth(300);
        
        feedbackTable.getColumns().clear();
        
        feedbackTable.getColumns().addAll(feedbackIdColumn, memberIdColumn, courseIdColumn, contentColumn, timeCreatedColumn);

        ObservableList<CourseFeedback> data = FXCollections.observableArrayList(courseFeedbacks);
        
        feedbackTable.setItems(data);
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		renderCourseFeedbackColumn(this.courseFeedbacks);
		
		totalFeedbackTitle.setText("Total feedbacks: ");
  		
  		totalDayFeedback.setText(this.courseFeedbacks.size() + "");
		
		datePicker.setOnAction(event -> {
    		  
              LocalDate selectedDate = datePicker.getValue();
              
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
              
              String formattedDate = selectedDate.format(formatter);
              
              List<CourseFeedback> filteredFeedbacks = this.courseFeedbacks.stream()
                      .filter(feedback -> feedback.getTimeCreated().contains(formattedDate))
                      .collect(Collectors.toList());
              
              renderCourseFeedbackColumn(filteredFeedbacks);
      		
    		totalFeedbackTitle.setText("Total selected day's feedbacks: ");
      		
      		totalDayFeedback.setText(filteredFeedbacks.size() + "");
        });
	}
}
