package main.java.equipment;

public class Equipment {
	
	private int equipmentId;
	
	private String equipmentName;
	
	private String equipmentStatus;
	
	private String equipmentMessage;
	
	private int roomId;
	
	public Equipment(int equipmentId, String equipmentName, String equipmentStatus, String equipmentMessage) {
		super();
		this.equipmentId = equipmentId;
		this.equipmentName = equipmentName;
		this.equipmentStatus = equipmentStatus;
		this.equipmentMessage = equipmentMessage;
	}

	public Equipment(int equipmentId, String equipmentName, String equipmentStatus, String equipmentMessage,
			int roomId) {
		super();
		this.equipmentId = equipmentId;
		this.equipmentName = equipmentName;
		this.equipmentStatus = equipmentStatus;
		this.equipmentMessage = equipmentMessage;
		this.roomId = roomId;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getEquipmentStatus() {
		return equipmentStatus;
	}

	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}

	public String getEquipmentMessage() {
		return equipmentMessage;
	}

	public void setEquipmentMessage(String equipmentMessage) {
		this.equipmentMessage = equipmentMessage;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
	
	
}
