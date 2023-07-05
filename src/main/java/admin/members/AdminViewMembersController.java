package main.java.admin.members;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import main.java.admin.memberProfile.AdminViewMemberProfileController;
import main.java.employee.members.EmployeeViewMembers;
import main.java.member.Member;

public class AdminViewMembersController extends EmployeeViewMembers{
	
	 @Override
	 protected void seeMemberDetail(Member member) {
    	
    	FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/ProfileScreen.fxml"));   
		
    	AdminViewMemberProfileController adminViewMemberProfileController = new AdminViewMemberProfileController(member);
			
	    loader1.setController(adminViewMemberProfileController);
	    
	    try {
			
			Parent fxml = loader1.load();
				
			allContentArea.getChildren().removeAll();
				
			allContentArea.getChildren().setAll(fxml);
				
		} catch (IOException e1) {
				
			e1.printStackTrace();
			
		}
    }
}
