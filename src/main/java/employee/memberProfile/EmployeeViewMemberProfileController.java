package main.java.employee.memberProfile;

import java.net.URL;
import java.util.ResourceBundle;

import main.java.member.Member;
import main.java.member.profile.MemberViewProfile;

public class EmployeeViewMemberProfileController extends MemberViewProfile{

	public EmployeeViewMemberProfileController(Member member) {
		super(member);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		super.initialize(arg0, arg1);
		
		memberWeightModifyBtn.setVisible(false);
		memberHeightModifyButton.setVisible(false);
		memberPhoneModifyBtn.setVisible(false);
	}
}
