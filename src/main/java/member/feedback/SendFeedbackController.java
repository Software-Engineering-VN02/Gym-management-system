package main.java.member.feedback;
import java.net.URL;
import java.util.ResourceBundle;

import database.GymSystemDB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SendFeedbackController implements Initializable{
			
		private String feedbackId;
		
		private String feedbackContent;

	  @FXML
	    private TextField idTextField;

	    @FXML
	    private StackPane allContentArea;

	    @FXML
	    private RadioButton employeeBtn;

	    @FXML
	    private Button sendBtn;

	    @FXML
	    private TextArea feedbackTextArea;

	    @FXML
	    private RadioButton equipmentBtn;

	    @FXML
	    private RadioButton roomBtn;

	    @FXML
	    private RadioButton courseBtn;
	    
	    @FXML
	    private Text sendStatusNoti;
	    
	    GymSystemDB gymSystemDB = GymSystemDB.GetInstance();
	    
	    private void sendFeedback() {
	    	
	    	if (roomBtn.isSelected()) {
	    		
	    		gymSystemDB.sendRoomFeedback(feedbackId, feedbackContent);
	    		
	    	} else if (courseBtn.isSelected()) {
	    		
	    		gymSystemDB.sendCourseFeedback(feedbackId, feedbackContent);
	    		
	    	} else if (employeeBtn.isSelected()) {
	    		
	    		gymSystemDB.sendEmployeeFeedback(feedbackId, feedbackContent);
	    		
	    	} else {
	    		
	    		gymSystemDB.sendEquipmentFeedback(feedbackId, feedbackContent);
	    	}
	    	
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			sendBtn.setOnMouseClicked(e -> {
				
				this.feedbackId = idTextField.getText();
				
				this.feedbackContent = feedbackTextArea.getText();
				
				if (this.feedbackId.length() > 0 && this.feedbackContent.length() > 0) {
					
					sendFeedback();
					sendStatusNoti.setText("Sucessfully sent feedback !!");
					sendStatusNoti.setFill(Color.BLUE);
					
				} else {
					
					sendStatusNoti.setText("Some required fields are empty, please check !!");
					sendStatusNoti.setFill(Color.RED);
				}
			});
			
			
		}

}
