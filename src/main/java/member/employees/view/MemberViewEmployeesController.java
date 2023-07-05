package main.java.member.employees.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import main.java.employee.Employee;

public class MemberViewEmployeesController implements Initializable{
	
	private List<Employee> employees = new ArrayList<>();
	
    public MemberViewEmployeesController(List<Employee> employees) {
		super();
		this.employees = employees;
	}

	@FXML
    private Label totalCoachesNumber;

    @FXML
    private TextField searchField;

    @FXML
    private Text searchValidNoti;

    @FXML
    protected TableView<Employee> coachesTable;

    @FXML
    private Button searchBtn;

    @FXML
    private RadioButton coachNameButton;
    
    @FXML
    private RadioButton roomIdButton;
    
    @SuppressWarnings("unchecked")
    
	protected void renderEmployees(List<Employee> employees) {
    	
    	TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
    	nameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
    	
    	nameColumn.setPrefWidth(250);
    	
    	TableColumn<Employee, String> genderColumn = new TableColumn<>("Gender");
    	genderColumn.setCellValueFactory(new PropertyValueFactory<>("employeeGender"));
    	
    	genderColumn.setPrefWidth(100);
    	
    	TableColumn<Employee, String> ageColumn = new TableColumn<>("Age");
    	ageColumn.setCellValueFactory(new PropertyValueFactory<>("employeeAge"));
    	
    	ageColumn.setPrefWidth(100);
    	
    	TableColumn<Employee, String> staffColumn = new TableColumn<>("Staff");
    	staffColumn.setCellValueFactory(new PropertyValueFactory<>("staff"));
    	
    	staffColumn.setPrefWidth(250);
    	
    	TableColumn<Employee, String> messageColumn = new TableColumn<>("Message");
    	messageColumn.setCellValueFactory(new PropertyValueFactory<>("employeeMessage"));
    	
    	messageColumn.setPrefWidth(300);
    	
    	TableColumn<Employee, String> roomColumn = new TableColumn<>("Room");
    	roomColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
    	
    	roomColumn.setPrefWidth(95);
    	
    	coachesTable.getColumns().clear();
    	
    	coachesTable.getColumns().addAll(nameColumn, genderColumn, ageColumn, staffColumn, messageColumn, roomColumn);
    	
    	ObservableList<Employee> data = FXCollections.observableArrayList(employees);
    	
    	coachesTable.setItems(data);
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		renderEmployees(this.employees);
		
		searchBtn.setOnMouseClicked(e -> {
			
			String searchValue = searchField.getText();
			
			if (searchValue.length() == 0) {
				renderEmployees(this.employees);
				
			} else {
				
				if ((coachNameButton.isSelected() == roomIdButton.isSelected()) || coachNameButton.isSelected() == true) {
					
					List<Employee> filteredEmployees = this.employees.stream().filter(employee -> employee.getEmployeeName().contains(searchValue)).collect(Collectors.toList());
					
					renderEmployees(filteredEmployees);
				} else {
					
					List<Employee> filteredEmployees = this.employees.stream().filter(employee -> employee.getRoomId() == Integer.parseInt(searchValue)).collect(Collectors.toList());
					
					renderEmployees(filteredEmployees);
				}
			}
		});
	}
}
