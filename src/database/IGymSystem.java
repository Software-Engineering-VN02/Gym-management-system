package database;

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

public interface IGymSystem {

	Room getRoomInfomation(String roomId);
	List<Room> getAllRooms();
	List<Course> getRoomCourses(String roomId);
	List<Member> getAllMembers();
	Member getMemberByMemberId(String memberId);
	List<Member> getMembersByCourseId(String courseId);
	List<Equipment> getEquipmentsByRoomId(String roomID);
	List<RoomFeedback> getRoomFeedbacks(String roomId, String day);
	List<Course> getMyCourses(String memberId);
	List<Employee> getEmployeesForMember();
	List<Employee> getEmployeesForAdmin();
	List<Service> getMemberUsageHistory(String memberId);
	Employee getEmployeeById(String employeeId);
	List<RoomFeedback> getRoomFeedbacks(String roomId);
	List<CourseFeedback> getCourseFeedbacks(String courseId);
	List<EmployeeFeedback> getEmployeeFeedbacks(String employeeId);
	void sendRoomFeedback(String roomId, String content);
	void sendCourseFeedback(String courseId, String content);
	void sendEmployeeFeedback(String employeeId, String content);
	void sendEquipmentFeedback(String equipmentId, String content);
	void modifyMemberHeight(String memberId, int height);
	void modifyMemberWeight(String memberId, int weight);
	void modifyMemberPhone(String memberId, String phone);
	void updateEquipmentStatus(String equipmentId, String status);
	void updateEquipmentMessage(String equipmentId, String message);
	void modifyEmployeeMessage(String employeeId, String content);
	void modifyEmployeePhone(String employeeId, String phone);
	void modifyEmployeeAddress(String employeeId, String address);
	void modifyCourseName(String courseId, String name);
	void modifyInChargeEmployeeId(String courseId, String id);
	void modifyCoursePrice(String courseId, String price);
	void modifyCourseType(String courseId, String courseType);
}
