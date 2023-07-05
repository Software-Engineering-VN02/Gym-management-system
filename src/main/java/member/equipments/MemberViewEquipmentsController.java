package main.java.member.equipments;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import main.java.equipment.Equipment;

public class MemberViewEquipmentsController implements Initializable{
	
	private List<Equipment> equipments = new ArrayList<>();
	
    public MemberViewEquipmentsController(List<Equipment> equipments) {
    	
		this.equipments = equipments;
	}
	@FXML
    private TextField searchField;

    @FXML
    private Text searchValidNoti;

    @FXML
    private Label totalEquipementsNumber;

    @FXML
    private Button searchBtn;

    @FXML
    protected TableView<Equipment> equipmentsTable;
    
    @FXML
    protected TableColumn<Equipment, Integer> idColumn = new TableColumn<>("Equipment ID");
    
    @FXML
    protected TableColumn<Equipment, String> nameColumn = new TableColumn<>("Equipment Name");
    
    @FXML
    protected TableColumn<Equipment, TableCell<Equipment, String>> statusColumn = new TableColumn<>("Equipment Status");

    @FXML
    protected TableColumn<Equipment, String> messageColumn = new TableColumn<>("Equipment Message");

    @FXML
    protected TableColumn<Equipment, Integer> roomIdColumn = new TableColumn<>("Room ID");
    
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	    idColumn.setCellValueFactory(new PropertyValueFactory<>("equipmentId"));
	    
	    idColumn.setPrefWidth(100);
	    
	    nameColumn.setCellValueFactory(new PropertyValueFactory<>("equipmentName"));
	    
	    nameColumn.setPrefWidth(250);
	    
	    statusColumn.setCellValueFactory(new PropertyValueFactory<>("equipmentStatus"));
	    
	    statusColumn.setPrefWidth(250);
			    
	    messageColumn.setCellValueFactory(new PropertyValueFactory<>("equipmentMessage"));
	    
	    messageColumn.setPrefWidth(400);
	    
	    roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
		
        // Add the columns to the TableView
        equipmentsTable.getColumns().addAll(idColumn, nameColumn, statusColumn, messageColumn, roomIdColumn);
        
        ObservableList<Equipment> data = FXCollections.observableArrayList(this.equipments);
        
        equipmentsTable.setItems(data);
        
        totalEquipementsNumber.setText(this.equipments.size() + "");
       
	}
}
