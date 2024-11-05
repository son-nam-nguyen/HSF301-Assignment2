package controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.Car;
import pojo.CarRental;
import pojo.CarRentalId;
import pojo.Customer;
import repository.CarRentalRepository;
import repository.CustomerRepository;
import repository.ICarRentalRepository;
import repository.ICustomerRepository;
import service.CarService;
import service.ICarService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import enums.CarStatus;
import enums.RentalStatus;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

public class RentalManagementController {
    private final ICustomerRepository icustomerRepository;
    private final ICarService iCarService;
    private final ICarRentalRepository iCarRentalRepository;

    // FXML fields
    @FXML
    private TextField txtCustomerId;
    @FXML
    private TextField txtCarId;
    @FXML
    private TextField txtRentalCustomerName;
    @FXML
    private ComboBox<String> carNameCombobox;
    @FXML
    private DatePicker dpPickupDate;
    @FXML
    private DatePicker dpReturnDate;
    @FXML
    private TextField txtRentalPrice;
    @FXML
    private ComboBox<String> comboboxStatus;
    @FXML
    private TableView<CarRental> tblRentalManagement;
    @FXML
    private TableColumn<CarRental, String> colCustomerId;
    @FXML
    private TableColumn<CarRental, String> colCarId;
    @FXML
    private TableColumn<CarRental, String> colRentalCustomerName;
    @FXML
    private TableColumn<CarRental, String> colRentalCarName;
    @FXML
    private TableColumn<CarRental, LocalDate> colPickupDate;
    @FXML
    private TableColumn<CarRental, LocalDate> colReturnDate;
    @FXML
    private TableColumn<CarRental, Double> colRentalPrice;
    @FXML
    private TableColumn<CarRental, String> colRentalStatus;

    private ObservableList<CarRental> tableModel;

    // Constructor
    public RentalManagementController() {
        icustomerRepository = new CustomerRepository("JPAs");
        iCarService = new CarService("JPAs");
        iCarRentalRepository = new CarRentalRepository("JPAs");
    }

    
    @FXML
    public void initialize() {
    	colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerID"));  
        colCarId.setCellValueFactory(new PropertyValueFactory<>("carID"));  
        colRentalCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colRentalCarName.setCellValueFactory(new PropertyValueFactory<>("carName"));
        colPickupDate.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colRentalPrice.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
        colRentalStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        carNameCombobox.getItems().addAll(
        		iCarService.getAllCars().stream()
                    .map(Car::getCarName)
                    .collect(Collectors.toList())
            );
        comboboxStatus.setItems(FXCollections.observableArrayList(
        	    Arrays.stream(RentalStatus.values()).map(Enum::name).collect(Collectors.toList())
        	));
        
        tableModel = FXCollections.observableArrayList(iCarRentalRepository.getAllRentals());
        tblRentalManagement.setItems(tableModel);
    }

    @FXML
    private void addOnAction() {
        try {
            Customer customer = icustomerRepository.findByEmail(txtRentalCustomerName.getText());
            Car car = iCarService.findByCarName(carNameCombobox.getValue());
            double rentalPrice = Double.parseDouble(txtRentalPrice.getText());
            if (customer != null && car != null) {
                CarRental carRental = new CarRental();
                carRental.setCustomer(customer);
                carRental.setCar(car);
                carRental.setPickupDate(dpPickupDate.getValue());
                carRental.setReturnDate(dpReturnDate.getValue());
                carRental.setRentPrice(rentalPrice);
                carRental.setStatus(RentalStatus.valueOf(comboboxStatus.getValue()));

                iCarRentalRepository.save(carRental);
                refreshTable();
                clearFields();
            } else {
                showAlert("Error", "Customer or Car not found.", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter a valid rental price.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "An error occurred: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    

    @FXML
    private void updateOnAction() {
        Integer customerId = Integer.parseInt(txtCustomerId.getText());
        Integer carId = Integer.parseInt(txtCarId.getText());
        CarRentalId rentalId = new CarRentalId(customerId, carId); 
        CarRental existingRental = iCarRentalRepository.findById(rentalId);
            existingRental.setPickupDate(dpPickupDate.getValue());
            existingRental.setReturnDate(dpReturnDate.getValue());
            existingRental.setRentPrice(Double.parseDouble(txtRentalPrice.getText()));
            existingRental.setStatus(RentalStatus.valueOf(comboboxStatus.getValue()));
            iCarRentalRepository.update(existingRental);
            refreshTable();
            clearFields();
       
    }



    @FXML
    private void cancelOnAction(ActionEvent event) {
        clearFields();
    }
    // Search action (implementation pending)
    @FXML
    private void searchOnAction(ActionEvent event) {
        // Add search functionality here
    }

    // Refresh table data
    private void refreshTable() {
        tableModel.clear();
        tableModel.addAll(iCarRentalRepository.getAllRentals());
    }

    // Clear all fields
    private void clearFields() {
        txtRentalCustomerName.clear();
        carNameCombobox.getSelectionModel().clearSelection();
        dpPickupDate.setValue(null);
        dpReturnDate.setValue(null);
        txtRentalPrice.clear();
        comboboxStatus.getSelectionModel().clearSelection();
    }

    // Utility method for showing alerts
    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    private void showRental(CarRental car) {
        colCustomerId.setText(String.valueOf(car.getCustomer().getCustomerID()));  
        colCarId.setText(String.valueOf(car.getCar().getCarID())); 
    	colRentalCustomerName.setText(car.getCustomer().getCustomerName());
    	colRentalCarName.setText(car.getCar().getCarName());
    	colPickupDate.setText(String.valueOf(car.getPickupDate()));
        colReturnDate.setText(String.valueOf(car.getReturnDate()));
        colRentalPrice.setText(String.valueOf(car.getRentPrice()));
        comboboxStatus.setValue(car.getStatus().name()); 
    }

}
