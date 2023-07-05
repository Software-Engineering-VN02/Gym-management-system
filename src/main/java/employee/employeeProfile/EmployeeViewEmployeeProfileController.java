package main.java.employee.employeeProfile;

import java.net.URL;
import java.util.ResourceBundle;

import main.java.employee.Employee;
import main.java.employee.myProfile.EmployeeViewMyProfileController;

public class EmployeeViewEmployeeProfileController extends EmployeeViewMyProfileController{

	public EmployeeViewEmployeeProfileController(Employee employee) {
		super(employee);
		// TODO Auto-generated constructor stub
	}

	@Override
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		super.initialize(arg0, arg1);
		
		employeePrideModifyBtn.setVisible(false);
		employeePhoneModifyBtn.setVisible(false);
		employeeAddressModifyBtn.setVisible(false);
		
	}
}
