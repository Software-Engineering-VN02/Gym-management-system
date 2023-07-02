package main.java.room;

public class Room {

	private int roomId;
	
	private String roomName;
	
	private int employee_in_charge_id;
	
	public Room(int roomId, String roomName, int employee_in_charge_id) {
		super();
		this.roomId = roomId;
		this.roomName = roomName;
		this.employee_in_charge_id = employee_in_charge_id;
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

	public int getEmployee_in_charge_id() {
		return employee_in_charge_id;
	}

	public void setEmployee_in_charge_id(int employee_in_charge_id) {
		this.employee_in_charge_id = employee_in_charge_id;
	}
	
	
}
