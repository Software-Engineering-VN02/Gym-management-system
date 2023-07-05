package main.java.feedback;

public class CourseFeedback {

		private int feedbackId;
		
		private int memberId;
		
		private int courseId;
		
		private String content;
		
		private String timeCreated;
	
		public CourseFeedback(int feedbackId, int memberId, String content, String timeCreated) {
			super();
			this.feedbackId = feedbackId;
			this.memberId = memberId;
			this.content = content;
			this.timeCreated = timeCreated;
		}
	
		public CourseFeedback(int feedbackId, int memberId, int courseId, String content, String timeCreated) {
			super();
			this.feedbackId = feedbackId;
			this.memberId = memberId;
			this.courseId = courseId;
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
	
		public int getCourseId() {
			return courseId;
		}
	
		public void setCourseId(int courseId) {
			this.courseId = courseId;
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
