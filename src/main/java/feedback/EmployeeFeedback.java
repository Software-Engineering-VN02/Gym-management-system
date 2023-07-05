package main.java.feedback;

public class EmployeeFeedback {
	
	private int feedbackId;
	
	private int memberId;
	
	private int employeeId;
	
	private String content;
	
	private String timeCreated;

	public EmployeeFeedback(int feedbackId, int memberId, String content, String timeCreated) {
		super();
		this.feedbackId = feedbackId;
		this.memberId = memberId;
		this.content = content;
		this.timeCreated = timeCreated;
	}

	public EmployeeFeedback(int feedbackId, int memberId, int employeeId, String content, String timeCreated) {
		super();
		this.feedbackId = feedbackId;
		this.memberId = memberId;
		this.employeeId = employeeId;
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

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		
		this.employeeId = employeeId;
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
