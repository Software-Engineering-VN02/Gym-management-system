package main.java.employee.myProfile;

import java.net.URL;
import java.util.ResourceBundle;

import database.GymSystemDB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import main.java.employee.Employee;


public class EmployeeViewMyProfileController implements Initializable{
	
	private Employee employee;
	
	public EmployeeViewMyProfileController(Employee employee) {
		super();
		this.employee = employee;
	}

	@FXML
    private Label employeePhone;

    @FXML
    private Label employeeName;

    @FXML
    protected ImageView employeeAddressModifyBtn;

    @FXML
    private Button saveChangesBtn;

    @FXML
    private Label employeeId;

    @FXML
    private Label employeeGender;

    @FXML
    private Label employeeAge;

    @FXML
    private StackPane memberHeightStackPane;

    @FXML
    private Label employeeAddress;

    @FXML
    private Label roomId;

    @FXML
    protected Label roomName;

    @FXML
	protected ImageView employeePrideModifyBtn;

    @FXML
    public ImageView employeePhoneModifyBtn;

    @FXML
    private StackPane allContentArea;

    @FXML
    private StackPane employeePhoneStackpane;

    @FXML
    private StackPane employeeAddressStackpane;

    @FXML
    private Label employeeEntryTime;

    @FXML
    private Label employeeStaff;

    @FXML
    private StackPane employeePrideStackpane;

    @FXML
    private Label employeePride;
    
    private void renderProfile() {
    	
    	employeeId.setText(employee.getEmployeeId() + "");
    	roomId.setText(employee.getRoomId() + "");
    	employeeName.setText(employee.getEmployeeName());
    	employeeGender.setText(employee.getEmployeeGender());
    	employeeAge.setText(employee.getEmployeeAge());
    	employeeEntryTime.setText(employee.getEntryTime());
    	employeeStaff.setText(employee.getStaff());
    	employeePride.setText(employee.getEmployeeMessage());
    	employeePhone.setText(employee.getPhone());
    	employeeAddress.setText(employee.getAddress());
    }
    
    GymSystemDB gymSystemDB = GymSystemDB.GetInstance();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		saveChangesBtn.setVisible(false);
		
		renderProfile();
		
        TextField employeePrideTextfield = new TextField();
        employeePrideTextfield.setText(employee.getEmployeeMessage() + "");
        
        TextField employeePhoneTextfield = new TextField();
        employeePhoneTextfield.setText(employee.getPhone());
        
        TextField employeeAddressTextfield = new TextField();
        employeeAddressTextfield.setText(employee.getAddress());
		
        employeePrideModifyBtn.setOnMouseClicked(e -> {
			
			saveChangesBtn.setVisible(true);
			
			employeePrideStackpane.getChildren().removeAll();
				        
			employeePrideStackpane.getChildren().setAll(employeePrideTextfield);

		});
		
        employeePhoneModifyBtn.setOnMouseClicked(e -> {
			
			saveChangesBtn.setVisible(true);
			
			employeePhoneStackpane.getChildren().removeAll();
				        
			employeePhoneStackpane.getChildren().setAll(employeePhoneTextfield);

		});
		
        employeeAddressModifyBtn.setOnMouseClicked(e -> {
			
			saveChangesBtn.setVisible(true);
			
			employeeAddressStackpane.getChildren().removeAll();
				        
			employeeAddressStackpane.getChildren().setAll(employeeAddressTextfield);

		});
		
		saveChangesBtn.setOnMouseClicked(e -> {
			
			if(employeePrideTextfield.isVisible() == true) {
				
				gymSystemDB.modifyEmployeeMessage(employee.getEmployeeId() + "", employeePrideTextfield.getText());
			}
			
			if(employeePhoneTextfield.isVisible() == true) {
				
				gymSystemDB.modifyEmployeePhone(employee.getEmployeeId() + "", employeePhoneTextfield.getText());
			}
			
			if(employeeAddressTextfield.isVisible() == true) {
				
				gymSystemDB.modifyEmployeeAddress(employee.getEmployeeId() + "", employeeAddressTextfield.getText());
			}
			
			memberHeightStackPane.getChildren().removeAll();
			
			employeePride.setText(employeePrideTextfield.getText());
	        
	        memberHeightStackPane.getChildren().setAll(employeePride);
	        
	        employeePhoneStackpane.getChildren().removeAll();
			
	        employeePhone.setText(employeePhoneTextfield.getText());
	        
	        employeePhoneStackpane.getChildren().setAll(employeePhone);
	        
	        employeeAddressStackpane.getChildren().removeAll();
			
	        employeeAddress.setText(employeeAddressTextfield.getText());
	        
			employeeAddressStackpane.getChildren().setAll(employeeAddress);	
						
		});
	}
}
