package main.java.admin.employeeFeedback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import main.java.feedback.EmployeeFeedback;

public class AdminViewEmployeeFeedbackController implements Initializable{
	
	private List<EmployeeFeedback> employeeFeedbacks = new ArrayList<>();
	
    public AdminViewEmployeeFeedbackController(List<EmployeeFeedback> employeeFeedbacks) {
		super();
		this.employeeFeedbacks = employeeFeedbacks;
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
    private TableView<EmployeeFeedback> feedbackTable;

	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		TableColumn<EmployeeFeedback, Integer> feedbackIdColumn = new TableColumn<>("Feedback ID");
		feedbackIdColumn.setPrefWidth(100);
		
		TableColumn<EmployeeFeedback, Integer> memberIdColumn = new TableColumn<>("Member ID");
		memberIdColumn.setPrefWidth(100);
		
		TableColumn<EmployeeFeedback, Integer> employeeIdColumn = new TableColumn<>("Employee ID");
		employeeIdColumn.setPrefWidth(100);
		
		TableColumn<EmployeeFeedback, String> contentColumn = new TableColumn<>("Content");
		contentColumn.setPrefWidth(400);
	
		TableColumn<EmployeeFeedback, String> timeCreatedColumn = new TableColumn<>("Time Created");
		timeCreatedColumn.setPrefWidth(250);

		// Set the cell value factories to extract property values from the EmployeeFeedback objects
		feedbackIdColumn.setCellValueFactory(new PropertyValueFactory<>("feedbackId"));
		memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));
		employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
		contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
		timeCreatedColumn.setCellValueFactory(new PropertyValueFactory<>("timeCreated"));
		
		feedbackTable.getColumns().addAll(feedbackIdColumn, memberIdColumn, employeeIdColumn, contentColumn, timeCreatedColumn);

        ObservableList<EmployeeFeedback> employeeFeedbackList = FXCollections.observableArrayList(this.employeeFeedbacks);

        feedbackTable.setItems(employeeFeedbackList);
	}

}
