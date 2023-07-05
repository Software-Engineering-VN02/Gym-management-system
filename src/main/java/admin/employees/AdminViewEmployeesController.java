package main.java.admin.employees;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import database.GymSystemDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import main.java.admin.employee.AdminViewEmployeeController;
import main.java.admin.employeeFeedback.AdminViewEmployeeFeedbackController;
import main.java.employee.Employee;
import main.java.feedback.EmployeeFeedback;
import main.java.member.employees.view.MemberViewEmployeesController;

public class AdminViewEmployeesController extends MemberViewEmployeesController implements Initializable{

    @FXML
    private StackPane contentArea;
	
	public AdminViewEmployeesController(List<Employee> employees) {
		super(employees);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderEmployees(List<Employee> employees) {
		
		TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
    	nameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
    	
    	nameColumn.setPrefWidth(200);
    	
    	TableColumn<Employee, String> genderColumn = new TableColumn<>("Gender");
    	genderColumn.setCellValueFactory(new PropertyValueFactory<>("employeeGender"));
    	
    	genderColumn.setPrefWidth(50);
    	
    	TableColumn<Employee, String> ageColumn = new TableColumn<>("Age");
    	ageColumn.setCellValueFactory(new PropertyValueFactory<>("employeeAge"));
    	
    	ageColumn.setPrefWidth(50);
    	
    	TableColumn<Employee, String> staffColumn = new TableColumn<>("Staff");
    	staffColumn.setCellValueFactory(new PropertyValueFactory<>("staff"));
    	
    	staffColumn.setPrefWidth(150);
    	
    	TableColumn<Employee, String> messageColumn = new TableColumn<>("Message");
    	messageColumn.setCellValueFactory(new PropertyValueFactory<>("employeeMessage"));
    	
    	messageColumn.setPrefWidth(200);
    	
    	TableColumn<Employee, String> roomColumn = new TableColumn<>("Room");
    	roomColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
    	
    	TableColumn<Employee, String> phoneColumn = new TableColumn<>("Phone Number");
    	phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
    	
    	TableColumn<Employee, String> addressColumn = new TableColumn<>("Address");
    	addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    	
    	roomColumn.setPrefWidth(95);
    	
    	 @SuppressWarnings("rawtypes")
		 TableColumn viewFeedbackCol = new TableColumn("Action");
	      
	     viewFeedbackCol.setCellValueFactory(new PropertyValueFactory<>("address"));

	     Callback<TableColumn<Employee, String>, TableCell<Employee, String>> cellFactory = new Callback<TableColumn<Employee, String>, TableCell<Employee, String>>() {
	    	 
	            @Override
	            public TableCell<Employee, String> call(final TableColumn<Employee, String> param) {
	            	
	                final TableCell<Employee, String> cell = new TableCell<Employee, String>() {

	                    final Button btn = new Button("See feedback");
	                    	                    
	                    @Override
	                    public void updateItem(String item, boolean empty) {
	                        super.updateItem(item, empty);
	                        
	                        if (empty) {
	                        	
	                            setGraphic(null);
	                            setText(null);
	                        } else {
	                        	
	                            btn.setOnAction(event -> {
	                            	
	                            	Employee employee = getTableView().getItems().get(getIndex());
	                            	
	                            	int id = employee.getEmployeeId();
	                            		                            	
	                            	FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/AdminViewEmployeeFeedback.fxml"));
	                            	
	                            	List<EmployeeFeedback> employeeFeedback1 = gymSystemDB.getEmployeeFeedbacks(id + "");
	                            	
	                                AdminViewEmployeeFeedbackController adminViewEmployeeFeedbackController = new AdminViewEmployeeFeedbackController(employeeFeedback1);
	                        			
	                        	    loader1.setController(adminViewEmployeeFeedbackController);
	                        			
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
	      
    	
    	coachesTable.getColumns().clear();
    	
    	coachesTable.getColumns().addAll(nameColumn, genderColumn, ageColumn, staffColumn, messageColumn, roomColumn, phoneColumn, addressColumn, viewFeedbackCol);
    	
    	ObservableList<Employee> data = FXCollections.observableArrayList(employees);
    	
    	coachesTable.setItems(data);
	}
	
	private void loadAfterNavigate(FXMLLoader loader) {
	  	
		try {
				
			Parent fxml = loader.load();
				
			contentArea.getChildren().removeAll();
				
			contentArea.getChildren().setAll(fxml);
				
		} catch (IOException e1) {
				
			e1.printStackTrace();
			
			}
	   }
	
	private void seeEmployeeProfile(Employee employee) {
		
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/EmployeeViewProfileScreen.fxml"));   
    			
		AdminViewEmployeeController adminViewEmployeeController = new AdminViewEmployeeController(employee);
			
	    loader1.setController(adminViewEmployeeController);
			
		loadAfterNavigate(loader1);
	}
	
	GymSystemDB gymSystemDB = GymSystemDB.GetInstance();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		
		coachesTable.setRowFactory( tv -> {
			
		    TableRow<Employee> row = new TableRow<>();
		    
		    row.setOnMouseClicked(event -> {
		    	
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		        	
		        	Employee rowData = row.getItem();
		        	
		        	List<Employee> employees = gymSystemDB.getEmployeesForAdmin().stream()
                            .filter(employee -> employee.getEmployeeId() == rowData.getEmployeeId())
                            .collect(Collectors.toList());
		        			        	
		        	Employee selectedEmployee = employees.get(0);
		        	
		        	seeEmployeeProfile(selectedEmployee);
		        	
		        }
		    });
		    return row ;
		});
		
		
	}

}
