package main.java.room;

public class Room {

	private int roomId;
	
	private String roomName;
	
	private int employeeInChargeId;
	
	private int employeeCount;
	
	private String employeeList;

	public void setEquipmentList(String equipmentList) {
		this.equipmentList = equipmentList;
	}

	public String getCourseList() {
		return courseList;
	}

	public void setCourseList(String courseList) {
		this.courseList = courseList;
	}

	private String equipmentList;
	
	private String courseList;
	
	public Room(int roomId, String roomName, int employeeInChargeId) {
		super();
		this.roomId = roomId;
		this.roomName = roomName;
		this.employeeInChargeId = employeeInChargeId;
	}

	public Room(int roomId, String roomName, int employeeInChargeId, int employeeCount, String employeeList,
			String equipmentList, String courseList) {
		super();
		this.roomId = roomId;
		this.roomName = roomName;
		this.employeeInChargeId = employeeInChargeId;
		this.employeeCount = employeeCount;
		this.employeeList = employeeList;
		this.equipmentList = equipmentList;
		this.courseList = courseList;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getEmployeeInChargeId() {
		return employeeInChargeId;
	}

	public void setEmployeeInChargeId(int employeeInChargeId) {
		this.employeeInChargeId = employeeInChargeId;
	}
	public int getEmployeeCount() {
		return employeeCount;
	}

	public void setEmployeeCount(int employeeCount) {
		this.employeeCount = employeeCount;
	}

	public String getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(String employeeList) {
		this.employeeList = employeeList;
	}

	public String getEquipmentList() {
		return equipmentList;
	}
	
}
