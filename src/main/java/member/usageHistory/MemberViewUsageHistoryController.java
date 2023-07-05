package main.java.member.usageHistory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.fxml.Initializable;
import main.java.service.Service;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class MemberViewUsageHistoryController implements Initializable{

	private List<Service> services = new ArrayList<>();
	
    public MemberViewUsageHistoryController(List<Service> services) {
		super();
		this.services = services;
	}

	@FXML
    private StackPane allContentArea;

    @FXML
    private TextField searchField;

    @FXML
    private Text searchValidNoti;

    @FXML
    private Button searchBtn;

    @FXML
    private TableView<Service> servicesTable;
	
	
	@SuppressWarnings("unchecked")
	private void renderUsageHistoryTable(List<Service> services) {
		
		TableColumn<Service, Integer> logIdColumn = new TableColumn<>("Log ID");
		logIdColumn.setCellValueFactory(new PropertyValueFactory<>("log_id"));

		// Member ID column
		TableColumn<Service, Integer> memberIdColumn = new TableColumn<>("Member ID");
		memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));

		// Service Name column
		TableColumn<Service, String> serviceNameColumn = new TableColumn<>("Service Name");
		serviceNameColumn.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
		
		serviceNameColumn.setPrefWidth(200);

		// Register Time column
		TableColumn<Service, String> registerTimeColumn = new TableColumn<>("Register Time");
		registerTimeColumn.setCellValueFactory(new PropertyValueFactory<>("registerTime"));
		
		registerTimeColumn.setPrefWidth(250);

		// Price column
		TableColumn<Service, Double> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		priceColumn.setPrefWidth(150);

		// Sale column
		TableColumn<Service, Double> saleColumn = new TableColumn<>("Sale");
		saleColumn.setCellValueFactory(new PropertyValueFactory<>("sale"));

		// Must Pay column
		TableColumn<Service, Double> mustPayColumn = new TableColumn<>("Must Pay");
		mustPayColumn.setCellValueFactory(new PropertyValueFactory<>("mustPay"));

		mustPayColumn.setPrefWidth(100);

		// Paid column
		TableColumn<Service, Double> paidColumn = new TableColumn<>("Paid");
		paidColumn.setCellValueFactory(new PropertyValueFactory<>("paid"));
		
		paidColumn.setPrefWidth(100);

		// Payment Method column
		TableColumn<Service, String> paymentMethodColumn = new TableColumn<>("Payment Method");
		paymentMethodColumn.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));

		servicesTable.getColumns().clear();
		
		// Add all columns to the TableView
		servicesTable.getColumns().addAll(logIdColumn, memberIdColumn, serviceNameColumn, registerTimeColumn,
		                              priceColumn, saleColumn, mustPayColumn, paidColumn, paymentMethodColumn);
		
		servicesTable.setItems(FXCollections.observableList(services));

	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		renderUsageHistoryTable(this.services);
		
		searchBtn.setOnMouseClicked(e -> {
			
			String searchValue = searchField.getText();
			
			if (searchValue.length() == 0) {
				
				renderUsageHistoryTable(this.services);
				
			} else {
				
				List<Service> filteredServicesList = this.services.stream()
		                .filter(service -> service.getServiceName().toLowerCase().contains(searchValue.toLowerCase()))
		                .collect(Collectors.toList());
				
				renderUsageHistoryTable(filteredServicesList);
			}
		});
		
	}
}
