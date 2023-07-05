package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.course.Course;
import main.java.employee.Employee;
import main.java.equipment.Equipment;
import main.java.feedback.CourseFeedback;
import main.java.feedback.EmployeeFeedback;
import main.java.feedback.RoomFeedback;
import main.java.member.Member;
import main.java.room.Room;
import main.java.service.Service;


public class GymSystemDB implements IGymSystem{
	
	protected Connection connection;
	protected Statement statement;
	protected ResultSet rs = null;
	
	private static GymSystemDB DB;
	
	public static GymSystemDB GetInstance() {
		
		if (DB == null) {  // First check
			 
            synchronized (GymSystemDB.class) {
            	
                if (DB == null) {  
                	DB = new GymSystemDB();
               }
          }
      }
		return DB;
	}
	

	public GymSystemDB() {
		
		String url = "jdbc:postgresql://localhost/gym_management";
		String user = "buidanhtung";
		String password = "Tung001202033042";
		
		try {
			
			this.connection = DriverManager.getConnection(url, user, password);
			this.statement = this.connection.createStatement();
			
		} catch (SQLException e) {
	        	
	         System.out.println(e.getMessage());
	    }
	}


	@Override
	public Room getRoomInfomation(String roomId) {
		
		 String query = "SELECT r.room_id, r.room_name, r.employee_in_charge_id, e.employee_count, e.employee_list, eq.equipment_list, string_agg(c.course_name, ', ') AS course_list\n"
		 		+ "FROM room r\n"
		 		+ "LEFT JOIN (\n"
		 		+ "    SELECT room_id, COUNT(*) AS employee_count, string_agg(employee_name, ', ') AS employee_list\n"
		 		+ "    FROM employee\n"
		 		+ "    GROUP BY room_id\n"
		 		+ ") e ON r.room_id = e.room_id\n"
		 		+ "LEFT JOIN (\n"
		 		+ "    SELECT room_id, string_agg(equipment_name, ', ') AS equipment_list\n"
		 		+ "    FROM equipment\n"
		 		+ "    GROUP BY room_id\n"
		 		+ ") eq ON r.room_id = eq.room_id\n"
		 		+ "LEFT JOIN course c ON r.room_id = c.coach_id \n"
		 		+ "WHERE r.room_id = "+ roomId + "\n"
		 		+ "GROUP BY r.room_id, r.room_name, e.employee_count, e.employee_list, eq.equipment_list;";

		    try {	    	
		    	ResultSet rs = this.statement.executeQuery(query);
		    	
		    	 while (rs.next()) {
		    		 
			    	 int roomID = rs.getInt("room_id");
			    	 String roomName = rs.getString("room_name");
			    	 int employeeInChargeId = rs.getInt("employee_in_charge_id");
			    	 int employeeCount = rs.getInt("employee_count");
			    	 String employeeList = rs.getString("employee_list");
			    	 String emquipmentList = rs.getString("equipment_list");
			    	 String courseList = rs.getString("course_list");
			    	 
			    	 return new Room(roomID, roomName, employeeInChargeId, employeeCount, employeeList, emquipmentList, courseList);
			      }
		    	 
		    } catch (SQLException e) {
	        	
		         System.out.println(e.getMessage());
		         
		    }
		    
		    return null;
	}


	@Override
	public List<Room> getAllRooms() {
		
		String query = "SELECT * FROM room;";

		List<Room> rooms = new ArrayList<>();
		
	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
	    		 
		    	 int roomID = rs.getInt("room_id");
		    	 String roomName = rs.getString("room_name");
		    	 int employeeInChargeId = rs.getInt("employee_in_charge_id");
		    	 
		    	 Room room =  new Room(roomID, roomName, employeeInChargeId);
		    	 
		    	 rooms.add(room);
		      }
	    	 
	    	 return rooms;
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	         
	    }
	    
	    return null;
	}


	@Override
	public List<Course> getRoomCourses(String roomId) {
	
		String query = "SELECT r.room_id, r.room_name, c.course_id, c.course_name, c.course_begin, c.price, c.course_type, c.schedule\n"
				+ ", e.employee_id as coach_id, e.employee_name as coach_name, e.employee_gender as coach_gender\n"
				+ "FROM room r\n"
				+ "LEFT JOIN employee e ON r.room_id = e.room_id\n"
				+ "LEFT JOIN course c ON e.employee_id = c.coach_id WHERE r.room_id = " + roomId +";";

		List<Course> courses = new ArrayList<>();
		
	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
	    		
		    	 int courseId = rs.getInt("course_id");
		    	 String courseName = rs.getString("course_name");
		    	 String courseBegin = rs.getString("course_begin");
		    	 int coachId = rs.getInt("coach_id");
		    	 String coachName = rs.getString("coach_name");
		    	 int price = rs.getInt("price");
		    	 String courseType = rs.getString("course_type");
		    	 String schedule = rs.getString("schedule");
		    	 
		    	 Course course =  new Course(courseId, courseName, courseBegin, coachId, coachName, price, courseType, schedule);
		    	 
		    	 courses.add(course);
		    	 
		      }
	    	 
	    	 return courses;
	    	
	    } catch (SQLException e) {
	    	
	         e.printStackTrace();
	         
	    }
	    
	    return null;
	}


	@Override
	public List<Member> getAllMembers() {
		
		String query = "SELECT * FROM member;";

		List<Member> members = new ArrayList<>();
		
	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
	    		 
		    	 int memberId = rs.getInt("member_id");
		    	 String memberName = rs.getString("member_name");
		    	 String memberGender = rs.getString("member_gender");
		    	 int memberAge = rs.getInt("member_age");
		    	 int memberHeight = rs.getInt("member_height");
		    	 int memberWeight = rs.getInt("member_weight");
		    	 String memberPhone = rs.getString("member_phone");
		    	 		    	 
		    	 Member member =  new Member(memberId, memberName, memberGender, memberAge, memberHeight, memberWeight, memberPhone);
		    	 
		    	 members.add(member);
		      }
	    	 
	    	 return members;
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	         
	    }
	    
	    return null;
	}


	@Override
	public Member getMemberByMemberId(String memberID) {

		String query = "SELECT * FROM member WHERE member_id = " + memberID + ";";
		
	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
	    		 
		    	 int memberId = rs.getInt("member_id");
		    	 String memberName = rs.getString("member_name");
		    	 String memberGender = rs.getString("member_gender");
		    	 int memberAge = rs.getInt("member_age");
		    	 int memberHeight = rs.getInt("member_height");
		    	 int memberWeight = rs.getInt("member_weight");
		    	 String memberPhone = rs.getString("member_phone");
		    	 		    	 
		    	 Member member =  new Member(memberId, memberName, memberGender, memberAge, memberHeight, memberWeight, memberPhone);
		    	 		    	 
		    	 return member;
		      }
	    	 
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	    }
	    
	    return null;
	}


	@Override
	public List<Member> getMembersByCourseId(String courseId) {
		
		String query = "SELECT m.*\n"
				+ "FROM member m\n"
				+ "JOIN course_order co ON m.member_account = co.member_account\n"
				+ "WHERE co.course_id = " + courseId + ";";
		
		List<Member> members = new ArrayList<>();
		
	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
	    		 
		    	 int memberId = rs.getInt("member_id");
		    	 String memberName = rs.getString("member_name");
		    	 String memberGender = rs.getString("member_gender");
		    	 int memberAge = rs.getInt("member_age");
		    	 int memberHeight = rs.getInt("member_height");
		    	 int memberWeight = rs.getInt("member_weight");
		    	 String memberPhone = rs.getString("member_phone");
		    	 		    	 
		    	 Member member =  new Member(memberId, memberName, memberGender, memberAge, memberHeight, memberWeight, memberPhone);
		    	 		    	 
		    	 members.add(member);
		      }
	    	 
	    	 return members;
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	    }
	    
	    return null;
	}


	@Override
	public List<Equipment> getEquipmentsByRoomId(String roomID) {
		
		String query = "SELECT * FROM equipment WHERE room_id =" + roomID + ";";
		
		List<Equipment> equipments = new ArrayList<>();
		
	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
	    		 
	    		 int equipmentId = rs.getInt("equipment_id");
	    		 String equipmentName = rs.getString("equipment_name");
	    		 String equipmentState = rs.getString("equipment_status");
	    		 String equipmentMessage = rs.getString("equipment_message");
		    
	    		 Equipment equipment =  new Equipment(equipmentId, equipmentName, equipmentState, equipmentMessage);
		    	 		    	 
		    	 equipments.add(equipment);
		      }
	    	 
	    	 return equipments;
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	    }
	    
	    return null;
	}


	@Override
	public List<RoomFeedback> getRoomFeedbacks(String roomID, String day) {
		
		String query = "SELECT m.*, fr.content, fr.time_created\n"
				+ "FROM member m\n"
				+ "JOIN feedback_room fr ON m.member_account = fr.member_account\n"
				+ "WHERE fr.room_id = " + roomID + " AND DATE_TRUNC('day', time_created) = '"  +  day + "'";
		
		List<RoomFeedback> feedbacks = new ArrayList<>();
		
	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
	    		 
	    		 int feedbackId = rs.getInt("feedback_id");
	    		 int memberId = rs.getInt("member_id");
	    		 String content = rs.getString("content");
	    		 String time = rs.getTimestamp("time_created").toString();
		    
	    		 RoomFeedback feedback =  new RoomFeedback(feedbackId, memberId, content, time);
		    	 		    	 
	    		 feedbacks.add(feedback);
		      }
	    	 
	    	 return feedbacks;
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	    }
	    
	    return null;
	}


	@Override
	public List<Course> getMyCourses(String memberId ) {
		
		String query = "SELECT c.course_id, c.course_name, c.course_begin, c.coach_id, e.employee_name AS coach_name, c.price, c.course_type, c.schedule\n"
				+ "FROM course_order co\n"
				+ "JOIN course c ON c.course_id = co.course_id\n"
				+ "JOIN member m ON m.member_account = co.member_account\n"
				+ "LEFT JOIN employee e ON e.employee_id = c.coach_id\n"
				+ "WHERE m.member_id = " + memberId + ";";
		
		List<Course> courses = new ArrayList<>();
		
	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
	    		 
	    		int courseId = rs.getInt("course_id");
	    		String courseName = rs.getString("course_name");
	    		String courseBegin = rs.getString("course_begin");
	    		int coachId = rs.getInt("coach_id");
	    		String coachName = rs.getString("coach_name");
	    		int price = rs.getInt("price");
	    		String courseType = rs.getString("course_type");
	    		String schedule = rs.getString("schedule");
		    
	    		 Course course =  new Course(courseId, courseName,courseBegin, coachId, coachName, price, courseType, schedule);
		    	 		    	 
	    		 courses.add(course);
		      }
	    	 
	    	 return courses;
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	    }
	    
	    return null;
	}


	@Override
	public List<Employee> getEmployeesForMember() {
		
		String query = "SELECT * FROM employee;";

		List<Employee> employees = new ArrayList<>();
		
	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
	    		 
		    	 String employeeName = rs.getString("employee_name");
		    	 String employeeGender = rs.getString("employee_gender");
		    	 String employeeAge = rs.getString("employee_age");
		    	 String staff = rs.getString("staff");
		    	 String employeeMessge = rs.getString("employee_message");
		    	 int roomId = rs.getInt("room_id");
		    	 
		    	 Employee employee =  new Employee(employeeName, employeeGender, employeeAge, staff, employeeMessge, roomId);
		    	 
		    	 employees.add(employee);
		      }
	    	 
	    	 return employees;
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	         
	    }
	    
	    return null;
	}


	@Override
	public void sendRoomFeedback(String roomId, String content) {
		
		String query = "INSERT INTO feedback_room(room_id, content, member_id)\n"
					   + "VALUES('" + roomId +"', '" + content + "', 1)";
		
	    try {	    	
	    	this.statement.executeQuery(query);
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	    }
	    
	}


	@Override
	public void sendCourseFeedback(String courseId, String content) {
		
		String query = "INSERT INTO feedback_course(course_id, content, member_id)\n"
				   + "VALUES('" + courseId +"', '" + content + "', 1)";
	
		 try {	    	
		 	this.statement.executeQuery(query);
		 	
		 } catch (SQLException e) {
		 	
		      System.out.println(e.getMessage());
		 }
		
	}


	@Override
	public void sendEmployeeFeedback(String employeeId, String content) {
		
		String query = "INSERT INTO feedback_employee(employee_id, content, member_id)\n"
				+ "VALUES('" + employeeId + "'," + content + "', 1)";
		
		try {	    	
		 	this.statement.executeQuery(query);
		 	
		 } catch (SQLException e) {
		 	
		      System.out.println(e.getMessage());
		 }
		
	}


	@Override
	public void sendEquipmentFeedback(String equipmentId, String content) {
		
		String query = "INSERT INTO feedback_equipment(equipment_id, content, member_id)\n"
				+ "VALUES('" + equipmentId + "'," + content + "', 1)";
		
		try {	    	
		 	this.statement.executeQuery(query);
		 	
		 } catch (SQLException e) {
		 	
		      System.out.println(e.getMessage());
		 }
		
	}


	@Override
	public void modifyMemberHeight(String memberId, int height) {
		
		String query = "UPDATE member SET member_height = " + height + " WHERE member_id = " + memberId + ";";
		
		try {	    	
		 	this.statement.executeQuery(query);
		 	
		 } catch (SQLException e) {
		 	
		      System.out.println(e.getMessage());
		 }
	}


	@Override
	public void modifyMemberWeight(String memberId, int weight) {
		
		String query = "UPDATE member SET member_weight = " + weight + " WHERE member_id = " + memberId + ";";
		
		try {
			
		 	this.statement.executeQuery(query);
		 	
		 } catch (SQLException e) {
		 	
		      System.out.println(e.getMessage());
		 }
	}


	@Override
	public void modifyMemberPhone(String memberId, String phone) {
		
		String query = "UPDATE member SET member_phone = '" + phone + "' WHERE member_id = " + memberId + ";";
		
		try {	    	
		 	this.statement.executeQuery(query);
		 	
		 } catch (SQLException e) {
		 	
		      System.out.println(e.getMessage());
		 }
		
	}


	@Override
	public List<Service> getMemberUsageHistory(String memberId) {
		
		String query = "SELECT * FROM usage_history_log WHERE member_id = " + memberId + ";";

		List<Service> services = new ArrayList<>();
		
	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
	    		 
		    	 int log_id = rs.getInt("log_id");
		    	 String serviceName = rs.getString("service_name");
		    	 String registerTime = rs.getString("register_time");
		    	 double price = rs.getDouble("price");
		    	 double sale = rs.getDouble("sale");
		    	 double mustPay = rs.getDouble("must_pay");
		    	 double paid = rs.getDouble("paid");
		    	 String paymentMethod = rs.getString("payment_method");
		    	 
		    	 Service service =  new Service(log_id, serviceName, registerTime, price, sale, mustPay, paid, paymentMethod);
		    	 
		    	 services.add(service);
		      }
	    	 
	    	 return services;
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	         
	    }
	    
	    return null;
	}


	@Override
	public void updateEquipmentStatus(String equipmentId, String status) {
		
		String query = "UPDATE equipment SET equipment_status = '" + status + "' WHERE equipment_id = " + equipmentId + ";";
		
		try {	    	
		 	this.statement.executeQuery(query);
		 	
		 } catch (SQLException e) {
		 	
		      System.out.println(e.getMessage());
		 }
		
	}


	@Override
	public void updateEquipmentMessage(String equipmentId, String message) {
	
		String query = "UPDATE equipment SET equipment_message = '" + message + "' WHERE equipment_id = " + equipmentId + ";";
		
		try {	    	
		 	this.statement.executeQuery(query);
		 	
		 } catch (SQLException e) {
		 	
		      System.out.println(e.getMessage());
		 }
	}


	@Override
	public Employee getEmployeeById(String employeeId) {
		
		String query = "SELECT * FROM employee WHERE employee_id = " + employeeId + ";";
		
	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
	    		 
		    	 String employeeName = rs.getString("employee_name");
		    	 String employeeGender = rs.getString("employee_gender");
		    	 String employeeAge = rs.getString("employee_age");
		    	 String employeeEntryTime = rs.getString("entry_time");
		    	 String staff = rs.getString("staff");
		    	 String employeeMessage = rs.getString("employee_message");
		    	 int roomId = rs.getInt("room_id");
		    	 String phone = rs.getString("phone");
		    	 String address = rs.getString("address");

		    	 
		    	 Employee employee =  new Employee(Integer.parseInt(employeeId) ,employeeName, employeeGender, employeeAge, employeeEntryTime, staff, employeeMessage, roomId, phone, address);
		    	 
		    	 return employee;
		      }
	    	 
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	         
	    }
	    
	    return null;
	}


	@Override
	public void modifyEmployeeMessage(String employeeId, String content) {
		
		String query = "UPDATE employee SET member_message = " + content + " WHERE employee_id = " + employeeId + ";";
		
		try {	    	
		 	this.statement.executeQuery(query);
		 	
		 } catch (SQLException e) {
		 	
		      System.out.println(e.getMessage());
		 }
		
	}


	@Override
	public void modifyEmployeePhone(String employeeId, String phone) {
		
		String query = "UPDATE employee SET phone = " + phone + " WHERE employee_id = " + employeeId + ";";
		
		try {	    	
		 	this.statement.executeQuery(query);
		 	
		 } catch (SQLException e) {
		 	
		      System.out.println(e.getMessage());
		 }
		
	}


	@Override
	public void modifyEmployeeAddress(String employeeId, String address) {
		
		String query = "UPDATE employee SET address = " + address + " WHERE employee_id = " + employeeId + ";";
		
		try {	    	
		 	this.statement.executeQuery(query);
		 	
		 } catch (SQLException e) {
		 	
		      System.out.println(e.getMessage());
		 }
		
	}


	@Override
	public List<RoomFeedback> getRoomFeedbacks(String roomId) {
		
		String query = "SELECT * FROM feedback_room WHERE room_id = " + roomId + " ORDER BY time_created DESC;";
		
		List<RoomFeedback> roomFeedbacks = new ArrayList<>();
		
	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
	    		 
		    	int feedbackId = rs.getInt("feedback_id");
		    	String content = rs.getString("content");
		    	int memberId = rs.getInt("member_id");
		    	String timeCreated  =rs.getString("time_created");

		    	 
		    	 RoomFeedback roomFeedback =  new RoomFeedback( feedbackId, memberId, Integer.parseInt(roomId), content, timeCreated);
		    	 
		    	 roomFeedbacks.add(roomFeedback);
		      }
	    	 
	    	return roomFeedbacks;
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	         
	    }
	    
	    return null;
	}


	@Override
	public List<CourseFeedback> getCourseFeedbacks(String courseId) {
		
		String query = "SELECT * FROM feedback_course WHERE course_id = " + courseId + "ORDER BY time_created DESC;";
		
		List<CourseFeedback> courseFeedbacks = new ArrayList<>();
		
	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
	    		 
		    	int feedbackId = rs.getInt("feedback_id");
		    	String content = rs.getString("content");
		    	int memberId = rs.getInt("member_id");
		    	String timeCreated  =rs.getString("time_created");

		    	 
		    	CourseFeedback courseFeedback =  new CourseFeedback( feedbackId, memberId, Integer.parseInt(courseId), content, timeCreated);
		    	 
		    	 courseFeedbacks.add(courseFeedback);
		      }
	    	 
	    	return courseFeedbacks;
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	         
	    }
	    
	    return null;
	}


	@Override
	public List<Employee> getEmployeesForAdmin() {
		
		String query = "SELECT * FROM employee;";

		List<Employee> employees = new ArrayList<>();
		
	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
	    		 
	    		 int employeeId = rs.getInt("employee_id");
		    	 String employeeName = rs.getString("employee_name");
		    	 String employeeGender = rs.getString("employee_gender");
		    	 String employeeAge = rs.getString("employee_age");
		    	 String entryTime = rs.getString("entry_time");
		    	 String staff = rs.getString("staff");
		    	 String employeeMessage = rs.getString("employee_message");
		    	 int roomId = rs.getInt("room_id");
		    	 String phone = rs.getString("phone");
		    	 String address = rs.getString("address");
		    	 
		    	 Employee employee =  new Employee(employeeId, employeeName, employeeGender, employeeAge, entryTime , staff, employeeMessage, roomId, phone, address);
		    	 
		    	 employees.add(employee);
		      }
	    	 
	    	 return employees;
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	         
	    }
	    
	    return null;
	}


	@Override
	public void modifyCourseName(String courseId, String name) {
		
		String query = "UPDATE course SET course_name = '" + name + "' WHERE course_id = " + courseId + ";";
		
		try {	    	
		 	this.statement.executeQuery(query);
		 	
		 } catch (SQLException e) {
		 	
		      System.out.println(e.getMessage());
		 }	
	}


	@Override
	public void modifyInChargeEmployeeId(String courseId, String id) {
		
		String query = "UPDATE course SET coach_id = " + id + " WHERE course_id = " + courseId + ";";
		
		try {	    	
		 	this.statement.executeQuery(query);
		 	
		 } catch (SQLException e) {
		 	
		      System.out.println(e.getMessage());
		 }	
		
	}


	@Override
	public void modifyCoursePrice(String courseId, String price) {
		
		String query = "UPDATE course SET price = " + price + " WHERE course_id = " + courseId + ";";
		
		try {	    	
		 	this.statement.executeQuery(query);
		 	
		 } catch (SQLException e) {
		 	
		      System.out.println(e.getMessage());
		 }	
		
	}


	@Override
	public void modifyCourseType(String courseId, String courseType) {
		// TODO Auto-generated method stub
		
		String query = "UPDATE course SET course_type = " + courseType + " WHERE course_id = " + courseId + ";";
		
		try {	    	
		 	this.statement.executeQuery(query);
		 	
		 } catch (SQLException e) {
		 	
		      System.out.println(e.getMessage());
		 }	
	}


	@Override
	public List<EmployeeFeedback> getEmployeeFeedbacks(String employeeId) {
			
		String query = "SELECT * FROM feedback_employee WHERE employee_id = " + employeeId + "ORDER BY time_created DESC;";
		
		List<EmployeeFeedback> employeeFeedbacks = new ArrayList<>();
		
	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
	    		 
		    	int feedbackId = rs.getInt("feedback_id");
		    	String content = rs.getString("content");
		    	int memberId = rs.getInt("member_id");
		    	String timeCreated  =rs.getString("time_created");

		    	 
		    	EmployeeFeedback employeeFeedback =  new EmployeeFeedback( feedbackId, memberId, Integer.parseInt(employeeId), content, timeCreated);
		    	 
		    	employeeFeedbacks.add(employeeFeedback);
		      }
	    	 
	    	return employeeFeedbacks;
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	         
	    }
	    
	    return null;
	}
}
