package main.java.member.myCourses;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import main.java.course.Course;

public class MemberViewMyCoursesController implements Initializable{
	
	private List<Course> myCourses = new ArrayList<>();

    public MemberViewMyCoursesController(List<Course> myCourses) {
		super();
		this.myCourses = myCourses;
	}

	@FXML
    private Label totalCoursesNumber;

    @FXML
    private Text searchValidNoti;

    @FXML
    private TableView<Course> coursesTable;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		  TableColumn<Course, Integer> courseIdCol = new TableColumn<>("Course ID");
	        courseIdCol.setCellValueFactory(new PropertyValueFactory<>("courseId"));

	        TableColumn<Course, String> courseNameCol = new TableColumn<>("Course Name");
	        courseNameCol.setCellValueFactory(new PropertyValueFactory<>("courseName"));
	        courseNameCol.setPrefWidth(100);

	        TableColumn<Course, String> courseBeginCol = new TableColumn<>("Course Begin");
	        courseBeginCol.setCellValueFactory(new PropertyValueFactory<>("courseBegin"));
	        
	        courseBeginCol.setPrefWidth(150);

	        TableColumn<Course, String> coachNameCol = new TableColumn<>("Coach Name");
	        coachNameCol.setCellValueFactory(new PropertyValueFactory<>("coachName"));
	        
	        coachNameCol.setPrefWidth(200);

	        TableColumn<Course, Integer> priceCol = new TableColumn<>("Price");
	        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
	        priceCol.setPrefWidth(100);

	        TableColumn<Course, String> courseTypeCol = new TableColumn<>("Course Type");
	        courseTypeCol.setCellValueFactory(new PropertyValueFactory<>("courseType"));
	        
	        courseTypeCol.setPrefWidth(200);

	        TableColumn<Course, String> scheduleCol = new TableColumn<>("Schedule");
	        scheduleCol.setCellValueFactory(new PropertyValueFactory<>("schedule"));
	        
	        scheduleCol.setPrefWidth(250);
	        
	        coursesTable.getColumns().addAll(courseIdCol, courseNameCol, courseBeginCol, coachNameCol,
	                priceCol, courseTypeCol, scheduleCol);

	        // Create an ObservableList of courses and add the created course
	        ObservableList<Course> courses = FXCollections.observableArrayList(this.myCourses);

	        coursesTable.setItems(courses);
	        
	        totalCoursesNumber.setText(courses.size() + "");
	}

}
