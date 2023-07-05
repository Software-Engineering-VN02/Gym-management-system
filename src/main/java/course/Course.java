package main.java.course;

public class Course {
	
	private int courseId;
	
	private String courseName;
	
	private String courseBegin;
		
	private int coachId;
	
	private String coachName;
	
	private int price;
	
	private String courseType;
	
	private String schedule;

	public Course(int courseId, String courseName, String courseBegin, int coachId, String coachName, int price,
			String courseType, String schedule) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseBegin = courseBegin;
		this.coachId = coachId;
		this.coachName = coachName;
		this.price = price;
		this.courseType = courseType;
		this.schedule = schedule;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseBegin() {
		return courseBegin;
	}

	public void setCourseBegin(String courseBegin) {
		this.courseBegin = courseBegin;
	}

	public int getCoachId() {
		return coachId;
	}

	public void setCoachId(int coachId) {
		this.coachId = coachId;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
}
