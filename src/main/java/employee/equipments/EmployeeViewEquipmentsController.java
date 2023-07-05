package main.java.employee.equipments;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import database.GymSystemDB;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import main.java.equipment.Equipment;
import main.java.member.equipments.MemberViewEquipmentsController;


public class EmployeeViewEquipmentsController extends MemberViewEquipmentsController implements Initializable{

	GymSystemDB gymSystemDB = GymSystemDB.GetInstance();
	
	public EmployeeViewEquipmentsController(List<Equipment> equipments) {
		super(equipments);
		
	}
	  private void openUpdateStateModalDialog(String updateId, String prevText) {
	        // Create a dialog
	        Dialog<String> dialog = new Dialog<>();
	        dialog.initModality(Modality.APPLICATION_MODAL);
	        dialog.setTitle("Edit Equipment Status");

	        // Create a dialog pane
	        DialogPane dialogPane = new DialogPane();
	        
	        dialogPane.setMinWidth(300);

	        VBox content = new VBox();

	        // Create a label
	        Label label = new Label("Enter new equipment Status:");

	        // Create a text field
	        TextField textField = new TextField();
	        
	        textField.setText(prevText);
	        
	        // Add the label and text field to the VBox
	        content.getChildren().addAll(label, textField);

	        // Set the content of the dialog pane
	        dialogPane.setContent(content);

	        // Set the dialog pane as the root of the dialog
	        dialog.setDialogPane(dialogPane);

	        // Add OK and Cancel buttons
	        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

	        // Handle the OK button action
	        dialog.setResultConverter(buttonType -> {
	            if (buttonType == ButtonType.OK) {
	            	
	            	gymSystemDB.updateEquipmentStatus(updateId, textField.getText());
	            	
	            }
	            return null;
	        });

	        // Show the dialog and wait for the result
	        String result = dialog.showAndWait().orElse(null);
	        
	        if (result != null) {
	            System.out.println("Entered value: " + result);
	        }
	    }
	  
	  private void openUpdateMessageModalDialog(String updateId, String prevText) {
	        // Create a dialog
	        Dialog<String> dialog = new Dialog<>();
	        dialog.initModality(Modality.APPLICATION_MODAL);
	        dialog.setTitle("Edit Equipment Message");

	        // Create a dialog pane
	        DialogPane dialogPane = new DialogPane();
	        
	        dialogPane.setMinWidth(300);

	        VBox content = new VBox();

	        // Create a label
	        Label label = new Label("Enter new equipment message:");

	        // Create a text field
	        TextField textField = new TextField();
	        
	        textField.setText(prevText);
	        
	        // Add the label and text field to the VBox
	        content.getChildren().addAll(label, textField);

	        // Set the content of the dialog pane
	        dialogPane.setContent(content);

	        // Set the dialog pane as the root of the dialog
	        dialog.setDialogPane(dialogPane);

	        // Add OK and Cancel buttons
	        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

	        // Handle the OK button action
	        dialog.setResultConverter(buttonType -> {
	            if (buttonType == ButtonType.OK) {
	            	
	            	gymSystemDB.updateEquipmentMessage(updateId, textField.getText());
	            	
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

		 equipmentsTable.setOnMouseClicked(event -> {
			 
			 if (event.getClickCount() == 2) {
			        @SuppressWarnings("unchecked")
					TablePosition<Equipment, ?> position = equipmentsTable.getSelectionModel().getSelectedCells().get(0);
			        
			        int rowIndex = position.getRow();
			        
			        TableColumn<Equipment, ?> column = position.getTableColumn();
			        Equipment selectedItem = equipmentsTable.getItems().get(rowIndex);
			        
			        // Perform the desired action based on the column and row index
			        if (column.getText().equals("Equipment Status")) {
			            // Get the content of the clicked cell
			            String equipmentStatus = column.getCellData(selectedItem).toString();
			            
			            TableColumn<Equipment, ?> firstColumn = equipmentsTable.getColumns().get(0);
			            String equipmentId = firstColumn.getCellData(selectedItem).toString();
			            // Perform further actions with the equipment status
			            			            
			            openUpdateStateModalDialog(equipmentId, equipmentStatus);
			        }
			        
			        if (column.getText().equals("Equipment Message")) {
			            // Get the content of the clicked cell
			            String equipmentMessage = column.getCellData(selectedItem).toString();
			            
			            TableColumn<Equipment, ?> firstColumn = equipmentsTable.getColumns().get(0);
			            String equipmentId = firstColumn.getCellData(selectedItem).toString();
			            // Perform further actions with the equipment status
			            			            
			            openUpdateMessageModalDialog(equipmentId, equipmentMessage);
			            
			        }
			        
			    }
		   });
	}
}
