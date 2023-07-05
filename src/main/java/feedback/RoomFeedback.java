package main.java.feedback;

public class RoomFeedback {

	private int feedbackId;
	
	private int memberId;
	
	private int roomId;
	
	private String content;
	
	private String timeCreated;

	public RoomFeedback(int feedbackId, int memberId, String content, String timeCreated) {
		super();
		this.feedbackId = feedbackId;
		this.memberId = memberId;
		this.content = content;
		this.timeCreated = timeCreated;
	}

	public RoomFeedback(int feedbackId, int memberId, int roomId, String content, String timeCreated) {
		super();
		this.feedbackId = feedbackId;
		this.memberId = memberId;
		this.roomId = roomId;
		this.content = content;
		this.timeCreated = timeCreated;
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}
}
