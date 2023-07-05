package main.java.member.profile;

import java.net.URL;
import java.util.ResourceBundle;

import database.GymSystemDB;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import main.java.member.Member;


public class MemberViewProfile implements Initializable{
	
	   private Member member;
		
	   public MemberViewProfile(Member member) {
			super();
			this.member = member;
		}

	@FXML
	    private StackPane memberWeightStackPane;

	    @FXML
	    private Label memberAge;

	    @FXML
	    private Label memberGender;

	    @FXML
	    private Label memberName;

	    @FXML
	    protected ImageView memberWeightModifyBtn;

	    @FXML
	    private StackPane memberHeightStackPane;

	    @FXML
	    protected ImageView memberPhoneModifyBtn;

	    @FXML
	    private Label memberHeight;

	    @FXML
	    private Label memberPhone;

	    @FXML
	    protected ImageView memberHeightModifyButton;

	    @FXML
	    private StackPane allContentArea;

	    @FXML
	    private Label memberWeight;

	    @FXML
	    private Label memberId;

	    @FXML
	    private StackPane memberPhoneStackPane;
	    
	    @FXML
	    private Button saveChangesBtn;
	
    GymSystemDB gymSystemDB = GymSystemDB.GetInstance();
    
    private void renderProfile() {
    	
    	memberId.setText(member.getMemberId() + "");
		memberName.setText(member.getMemberName() + "");
		memberGender.setText(member.getMemberGender() + "");
		memberAge.setText(member.getMemberAge() + "");
		memberHeight.setText(member.getMemberHeight() + "");
		memberWeight.setText(member.getMemberWeight() + "");
		memberPhone.setText(member.getMemberPhone() + "");
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		saveChangesBtn.setVisible(false);
			
		renderProfile();
		
        TextField memberHeightTextField = new TextField();
        memberHeightTextField.setText(member.getMemberHeight() + "");
        
        TextField memberWeightTextField = new TextField();
        memberWeightTextField.setText(member.getMemberWeight() + "");
        
        TextField memberPhoneTextField = new TextField();
        memberPhoneTextField.setText(member.getMemberPhone() + "");
		
		memberHeightModifyButton.setOnMouseClicked(e -> {
			
			saveChangesBtn.setVisible(true);
			
			memberHeightStackPane.getChildren().removeAll();
				        
	        memberHeightStackPane.getChildren().setAll(memberHeightTextField);

		});
		
		memberWeightModifyBtn.setOnMouseClicked(e -> {
			
			saveChangesBtn.setVisible(true);
			
			memberWeightStackPane.getChildren().removeAll();
				        
	        memberWeightStackPane.getChildren().setAll(memberWeightTextField);

		});
		
		memberPhoneModifyBtn.setOnMouseClicked(e -> {
			
			saveChangesBtn.setVisible(true);
			
			memberPhoneStackPane.getChildren().removeAll();
				        
	        memberPhoneStackPane.getChildren().setAll(memberPhoneTextField);

		});
		
		saveChangesBtn.setOnMouseClicked(e -> {
			
			if(memberHeightTextField.isVisible() == true) {
				
				gymSystemDB.modifyMemberHeight("1", Integer.parseInt(memberHeightTextField.getText()));
			}
			
			if(memberWeightTextField.isVisible() == true) {
				
				gymSystemDB.modifyMemberWeight("1", Integer.parseInt(memberWeightTextField.getText()));
			}
			
			if(memberPhoneTextField.isVisible() == true) {
				
				gymSystemDB.modifyMemberPhone("1", memberPhoneTextField.getText());
			}
			
			memberHeightStackPane.getChildren().removeAll();
			
			memberHeight.setText(memberHeightTextField.getText());
	        
	        memberHeightStackPane.getChildren().setAll(memberHeight);
	        
	        memberWeightStackPane.getChildren().removeAll();
			
			memberWeight.setText(memberWeightTextField.getText());
	        
			memberWeightStackPane.getChildren().setAll(memberWeight);
	        
	        memberPhoneStackPane.getChildren().removeAll();
			
			memberPhone.setText(memberPhoneTextField.getText());
	        
			memberPhoneStackPane.getChildren().setAll(memberPhone);	
						
		});
	}

}
