package main.java.employee.members;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import database.GymSystemDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import main.java.employee.memberProfile.EmployeeViewMemberProfileController;
import main.java.member.Member;

public class EmployeeViewMembers implements Initializable {
	
		private List<Member> members = new ArrayList<>();
		
	    @FXML
	    private Label totalMembersTitle;
	
	   @FXML
	    private TextField searchField;

	    @FXML
	    private Text searchValidNoti;

	    @FXML
	    private Button searchBtn;

	    @FXML
	    private RadioButton memberIdButton;

	    @FXML
	    private TableView<Member> MembersTable;

	    @FXML
	    private RadioButton courseIdButton;

	    @FXML
	    private Label totalMembersNumber;
	    
	    @FXML
	    private Label courseTotalMemberTitle;

	    @FXML
	    private Label courseTotalMember;
	    
	    @FXML
	    protected StackPane allContentArea;
	    
	    GymSystemDB gymSystemDb = GymSystemDB.GetInstance();

	    @SuppressWarnings("unchecked")
		private void renderMembers(List<Member> members) {
	    		    	
	    	TableColumn<Member, Integer> idColumn = new TableColumn<>("Member ID");
	    	idColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));

	    	TableColumn<Member, String> nameColumn = new TableColumn<>("Member Name");
	    	nameColumn.setCellValueFactory(new PropertyValueFactory<>("memberName"));
	    	nameColumn.setPrefWidth(250);

	    	TableColumn<Member, String> genderColumn = new TableColumn<>("Gender");
	    	genderColumn.setCellValueFactory(new PropertyValueFactory<>("memberGender"));
	    	genderColumn.setPrefWidth(150);

	    	TableColumn<Member, Integer> ageColumn = new TableColumn<>("Age");
	    	ageColumn.setCellValueFactory(new PropertyValueFactory<>("memberAge"));
	    	ageColumn.setPrefWidth(100);

	    	TableColumn<Member, Integer> heightColumn = new TableColumn<>("Height");
	    	heightColumn.setCellValueFactory(new PropertyValueFactory<>("memberHeight"));
	    	heightColumn.setPrefWidth(100);

	    	TableColumn<Member, Integer> weightColumn = new TableColumn<>("Weight");
	    	weightColumn.setCellValueFactory(new PropertyValueFactory<>("memberWeight"));
	    	weightColumn.setPrefWidth(100);

	    	TableColumn<Member, String> phoneColumn = new TableColumn<>("Phone");
	    	phoneColumn.setCellValueFactory(new PropertyValueFactory<>("memberPhone"));
	    	phoneColumn.setPrefWidth(300);

	    	// Add columns to the table
	    	MembersTable.getColumns().clear();
	    	
	    	MembersTable.getColumns().addAll(idColumn, nameColumn, genderColumn, ageColumn, heightColumn, weightColumn, phoneColumn);
	    	
	    	totalMembersNumber.setText(members.size() + "");
	    	
	 	    ObservableList<Member> data = FXCollections.observableArrayList(members);

	    	MembersTable.setItems(data);
	    }
	    
	    protected void seeMemberDetail(Member member) {
	    	
	    	FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/ProfileScreen.fxml"));   
			
		    EmployeeViewMemberProfileController EmployeeViewMemberProfileController = new EmployeeViewMemberProfileController(member);
				
		    loader1.setController(EmployeeViewMemberProfileController);
		    
		    try {
				
				Parent fxml = loader1.load();
					
				allContentArea.getChildren().removeAll();
					
				allContentArea.getChildren().setAll(fxml);
					
			} catch (IOException e1) {
					
				e1.printStackTrace();
				
			}
	    }
	    
	    GymSystemDB gymSystemDB = GymSystemDB.GetInstance();
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			courseTotalMemberTitle.setVisible(false);
			
			courseTotalMember.setText("");

	    	this.members =  gymSystemDb.getAllMembers();

			renderMembers(this.members);
			
			searchBtn.setOnMouseClicked(e -> {
				
				String searchValue = searchField.getText();
				
				if (searchValue.length() == 0) {
					
					renderMembers(members);

				} else {
					
					if((courseIdButton.isSelected() == memberIdButton.isSelected() ) || (memberIdButton.isSelected() == true && courseIdButton.isSelected() == false)) {
						
						Member member = gymSystemDb.getMemberByMemberId(searchValue);
						
						this.members.clear();
						
						this.members.add(member);
						
						renderMembers(this.members);
						
						courseTotalMemberTitle.setVisible(false);
						
						courseTotalMember.setText("");
						
						totalMembersTitle.setVisible(true);
						
						totalMembersNumber.setVisible(true);
						
					} else if (courseIdButton.isSelected() == true && memberIdButton.isSelected() == false) {
												
						List<Member> members = gymSystemDb.getMembersByCourseId(searchValue);
												
						this.members = members;
						
						totalMembersTitle.setVisible(false);
						
						totalMembersNumber.setVisible(false);
						
						renderMembers(this.members);
						
						courseTotalMemberTitle.setVisible(true);
						
						courseTotalMember.setText(members.size() + "");
					}
				}
			});
			
			MembersTable.setRowFactory( tv -> {
				
			    TableRow<Member> row = new TableRow<>();
			    
			    row.setOnMouseClicked(event -> {
			    	
			        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
			        	
			        	Member member = row.getItem();
			        	
			        	Member memberDetail = gymSystemDB.getMemberByMemberId(member.getMemberId() + "");
			        			        				        				        			        	
			        	seeMemberDetail(memberDetail);
			        	
			        }
			    });
			    return row ;
			});
		}

}
