package main.java.employee;

public class Employee {
	
	private int employeeId;
	
	private String employeeName;
	
	private String employeeGender;
	
	private String employeeAge;
	
	private String entryTime;
	
	private String staff;
	
	private String employeeMessage;
	
	private int roomId;
	
	private String phone;
	
	private String address;
	
	public Employee(String employeeName, String employeeGender, String employeeAge, String staff,
			String employeeMessage, int roomId) {
		
		super();
		this.employeeName = employeeName;
		this.employeeGender = employeeGender;
		this.employeeAge = employeeAge;
		this.staff = staff;
		this.employeeMessage = employeeMessage;
		this.roomId = roomId;
	}
	
	public Employee(int employeeId, String employeeName, String employeeGender, String employeeAge, String entryTime,
			String staff, String employeeMessage, int roomId, String phone, String address) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeGender = employeeGender;
		this.employeeAge = employeeAge;
		this.entryTime = entryTime;
		this.staff = staff;
		this.employeeMessage = employeeMessage;
		this.roomId = roomId;
		this.phone = phone;
		this.address = address;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeGender() {
		return employeeGender;
	}

	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}

	public String getEmployeeAge() {
		return employeeAge;
	}

	public void setEmployeeAge(String employeeAge) {
		this.employeeAge = employeeAge;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public String getEmployeeMessage() {
		return employeeMessage;
	}

	public void setEmployeeMessage(String employeeMessage) {
		this.employeeMessage = employeeMessage;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
