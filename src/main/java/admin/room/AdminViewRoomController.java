package main.java.admin.room;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import database.GymSystemDB;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import main.java.course.Course;
import main.java.employee.room.EmployeeViewRoomController;
import main.java.equipment.Equipment;
import main.java.room.Room;

public class AdminViewRoomController extends EmployeeViewRoomController{

	public AdminViewRoomController(Room room, List<Course> roomCourses, List<Equipment> roomEquipments) {
		super(room, roomCourses, roomEquipments);
		
	}
	
	GymSystemDB gymSystemDB = GymSystemDB.GetInstance();
	
	 private void openUpdateCouseNameModal(String courseId, String prevText) {

		 	Dialog<String> dialog = new Dialog<>();
	        dialog.initModality(Modality.APPLICATION_MODAL);
	        dialog.setTitle("Edit Course Name");

	        // Create a dialog pane
	        DialogPane dialogPane = new DialogPane();
	        
	        dialogPane.setMinWidth(300);

	        VBox content = new VBox();

	        // Create a label
	        Label label = new Label("Enter new course name:");

	        // Create a text field
	        TextField textField = new TextField();
	        
	        textField.setText(prevText);
	        
	        // Add the label and text field to the VBox
	        content.getChildren().addAll(label, textField);

	        // Set the content of the dialog pane
	        dialogPane.setContent(content);

	        // Set the dialog pane as the root of the dialog
	        dialog.setDialogPane(dialogPane);

	        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

	        dialog.setResultConverter(buttonType -> {
	            if (buttonType == ButtonType.OK) {
	            	
	            	gymSystemDB.modifyCourseName(courseId, textField.getText());
	            	
	            }
	            return null;
	        });

	        // Show the dialog and wait for the result
	        String result = dialog.showAndWait().orElse(null);
	        
	        if (result != null) {
	            System.out.println("Entered value: " + result);
	        }
	    }
	 
	 private void openUpdateCoachIdModal(String courseId, String prevText) {

		 	Dialog<String> dialog = new Dialog<>();
	        dialog.initModality(Modality.APPLICATION_MODAL);
	        dialog.setTitle("Edit Coach Id");

	        // Create a dialog pane
	        DialogPane dialogPane = new DialogPane();
	        
	        dialogPane.setMinWidth(300);

	        VBox content = new VBox();

	        // Create a label
	        Label label = new Label("Enter new coach Id:");

	        // Create a text field
	        TextField textField = new TextField();
	        
	        textField.setText(prevText);
	        
	        // Add the label and text field to the VBox
	        content.getChildren().addAll(label, textField);

	        // Set the content of the dialog pane
	        dialogPane.setContent(content);

	        // Set the dialog pane as the root of the dialog
	        dialog.setDialogPane(dialogPane);

	        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

	        dialog.setResultConverter(buttonType -> {
	            if (buttonType == ButtonType.OK) {
	            	
	            	gymSystemDB.modifyInChargeEmployeeId(courseId, textField.getText());
	            	
	            }
	            return null;
	        });

	        // Show the dialog and wait for the result
	        String result = dialog.showAndWait().orElse(null);
	        
	        if (result != null) {
	            System.out.println("Entered value: " + result);
	        }
	    }
	 
	 private void openUpdateCoachTypeModal(String courseId, String prevText) {

		 	Dialog<String> dialog = new Dialog<>();
	        dialog.initModality(Modality.APPLICATION_MODAL);
	        dialog.setTitle("Edit Course type");

	        // Create a dialog pane
	        DialogPane dialogPane = new DialogPane();
	        
	        dialogPane.setMinWidth(300);

	        VBox content = new VBox();

	        // Create a label
	        Label label = new Label("Enter new course type:");

	        // Create a text field
	        TextField textField = new TextField();
	        
	        textField.setText(prevText);
	        
	        // Add the label and text field to the VBox
	        content.getChildren().addAll(label, textField);

	        // Set the content of the dialog pane
	        dialogPane.setContent(content);

	        // Set the dialog pane as the root of the dialog
	        dialog.setDialogPane(dialogPane);

	        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

	        dialog.setResultConverter(buttonType -> {
	            if (buttonType == ButtonType.OK) {
	            	
	            	gymSystemDB.modifyCourseType(courseId, textField.getText());
	            	
	            }
	            return null;
	        });

	        // Show the dialog and wait for the result
	        String result = dialog.showAndWait().orElse(null);
	        
	        if (result != null) {
	            System.out.println("Entered value: " + result);
	        }
	    }
	 
	 private void openUpdatePricedModal(String courseId, String prevText) {

		 	Dialog<String> dialog = new Dialog<>();
	        dialog.initModality(Modality.APPLICATION_MODAL);
	        dialog.setTitle("Edit Course price");

	        // Create a dialog pane
	        DialogPane dialogPane = new DialogPane();
	        
	        dialogPane.setMinWidth(300);

	        VBox content = new VBox();

	        // Create a label
	        Label label = new Label("Enter new price:");

	        // Create a text field
	        TextField textField = new TextField();
	        
	        textField.setText(prevText);
	        
	        // Add the label and text field to the VBox
	        content.getChildren().addAll(label, textField);

	        // Set the content of the dialog pane
	        dialogPane.setContent(content);

	        // Set the dialog pane as the root of the dialog
	        dialog.setDialogPane(dialogPane);

	        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

	        dialog.setResultConverter(buttonType -> {
	            if (buttonType == ButtonType.OK) {
	            	
	            	gymSystemDB.modifyCoursePrice(courseId, textField.getText());
	            	
	            }
	            return null;
	        });

	        // Show the dialog and wait for the result
	        String result = dialog.showAndWait().orElse(null);
	        
	        if (result != null) {
	            System.out.println("Entered value: " + result);
	        }
	    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		super.initialize(arg0, arg1);
				
		coursesTable.setOnMouseClicked(event -> {
						 
			 if (event.getClickCount() == 2) {
				 				 
			        @SuppressWarnings("unchecked")
					TablePosition<Course, ?> position = coursesTable.getSelectionModel().getSelectedCells().get(0);
			        
			        int rowIndex = position.getRow();
			        
			        TableColumn<Course, ?> column = position.getTableColumn();
			        Course selectedItem = coursesTable.getItems().get(rowIndex);
			        
			        System.out.print(column.getText());
			        			        
			        if (column.getText().equals("Course name")) {

			        	String courseName = column.getCellData(selectedItem).toString();
			            
			            TableColumn<Course, ?> firstColumn = coursesTable.getColumns().get(0);
			            String courseId = firstColumn.getCellData(selectedItem).toString();
			            
			            openUpdateCouseNameModal(courseId, courseName);

			        }
			        
			        if (column.getText().equals("Coach Id")) {
			        	
			            String coachId = column.getCellData(selectedItem).toString();
			            
			            TableColumn<Course, ?> firstColumn = coursesTable.getColumns().get(0);
			            String courseId = firstColumn.getCellData(selectedItem).toString();
			            // Perform further actions with the equipment status
			            			            
			            openUpdateCoachIdModal(courseId, coachId);			            
			        }
			        
			        if (column.getText().equals("Price")) {
			        	
			            String price = column.getCellData(selectedItem).toString();
			            
			            TableColumn<Course, ?> firstColumn = coursesTable.getColumns().get(0);
			            String courseId = firstColumn.getCellData(selectedItem).toString();
			            // Perform further actions with the equipment status
			            			            
			            openUpdatePricedModal(courseId, price);			            
			        }
			        
			        if (column.getText().equals("Course type")) {
			        	
			            String courseType = column.getCellData(selectedItem).toString();
			            
			            TableColumn<Course, ?> firstColumn = coursesTable.getColumns().get(0);
			            String courseId = firstColumn.getCellData(selectedItem).toString();
			            // Perform further actions with the equipment status
			            			            
			            openUpdateCoachTypeModal(courseId, courseType);			            
			        }
			    }
		   });	
	
	}
}
