package controller;

import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import enums.CarStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.Car;
import pojo.CarProducer;

import service.CarProducerService;
import service.CarService;
import service.ICarProducerService;
import service.ICarService;

public class CarManagementController implements Initializable {
    private ICarProducerService iCarProducerService;
    private ICarService iCarService;

    @FXML
    private TextField txtCarId;         // For Car ID
    @FXML
    private TextField txtCarName;       // For Car Name
    @FXML
    private TextField txtCarModelYear;  // For Car Model Year
    @FXML
    private TextField txtColor;         // For Color
    @FXML
    private TextField txtCapacity;      // For Capacity
    @FXML
    private TextField txtRentPrice;     // For Rent Price

    @FXML
    private ComboBox<String> statusCombobox;  // For Status
    @FXML
    private ComboBox<String> productCombobox; // For Product

    @FXML
    private Button btnAdd;               // Add Button
    @FXML
    private Button btnUpdate;            // Update Button
    @FXML
    private Button btnDelete;            // Delete Button
    @FXML
    private Button btnCancel;            // Cancel Button
    @FXML
    private TableView<Car> tblCarManagements;
    @FXML
    private TableColumn<Car, String> colCarId;
    @FXML
    private TableColumn<Car, String> colCarName;
    @FXML
    private TableColumn<Car, String> colCarModelYear;
    @FXML
    private TableColumn<Car, String> colColor;
    @FXML
    private TableColumn<Car, Integer> colCapacity;
    @FXML
    private TableColumn<Car, Double> colRentPrice;
    @FXML
    private TableColumn<Car, String> colProduct;
    @FXML
    private TableColumn<Car, String> colStatus;

    private ObservableList<Car> tableModel;

    public CarManagementController() {
        iCarProducerService = new CarProducerService("JPAs");
        iCarService = new CarService("JPAs");
        tableModel = FXCollections.observableArrayList(iCarService.getAllCars());
    }

    @FXML
    private void addOnAction() {
    	CarProducer carp = iCarProducerService.findByProducerName(productCombobox.getValue());
        Car newCar = new Car();
        newCar.setCarName(txtCarName.getText());
        newCar.setCarModelYear(Integer.parseInt(txtCarModelYear.getText()));
        newCar.setColor(txtColor.getText());
        newCar.setCapacity(Integer.parseInt(txtCapacity.getText()));
        newCar.setRentPrice(Double.parseDouble(txtRentPrice.getText()));
        newCar.setStatus(CarStatus.valueOf(statusCombobox.getValue())); // Adjust if necessary
        newCar.setDescription("Default description");  // Set a default if necessary
        newCar.setImportDate(new Date());
       
        // Set the producer based on the selected producer
         newCar.setProducerID(carp); // Assuming producerID is CarProducer
        
        iCarService.save(newCar);
        refreshTable();
        clearFields();
    }

    @Override
    public void initialize(java.net.URL arg0, ResourceBundle arg1) {
        colCarId.setCellValueFactory(new PropertyValueFactory<>("carID"));
        colCarName.setCellValueFactory(new PropertyValueFactory<>("carName"));
        colCarModelYear.setCellValueFactory(new PropertyValueFactory<>("carModelYear"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        colRentPrice.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
        colProduct.setCellValueFactory(new PropertyValueFactory<>("producerName"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        statusCombobox.getItems().addAll(
            Arrays.stream(CarStatus.values()).map(Enum::name).toArray(String[]::new)
        );

        productCombobox.getItems().addAll(
            iCarProducerService.getAllCarProducer().stream()
                .map(CarProducer::getProducerName)
                .collect(Collectors.toList())
        );

        tblCarManagements.setItems(tableModel);
        tblCarManagements.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Car>() {
            @Override
            public void changed(ObservableValue<? extends Car> observableValue, Car oldValue, Car selectedCar) {
                if (selectedCar != null) {
                    showCar(selectedCar);
                }
            }
        });
    }

    private void refreshTable() {
        tableModel.clear();
        tableModel.addAll(iCarService.getAllCars());
    }

    protected void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showCar(Car car) {
        txtCarId.setText(String.valueOf(car.getCarID()));
        txtCarName.setText(car.getCarName());
        txtCarModelYear.setText(String.valueOf(car.getCarModelYear()));
        txtColor.setText(car.getColor());
        txtCapacity.setText(String.valueOf(car.getCapacity()));
        txtRentPrice.setText(String.valueOf(car.getRentPrice()));
        productCombobox.setValue(car.getProducerID().getProducerName()); // Adjust according to your Car model
        statusCombobox.setValue(car.getStatus().name());
    }

    @FXML
    private void updateOnAction() {
    	String name = productCombobox.getValue();
        CarProducer carProducer = iCarProducerService.findByProducerName(name);
        Car car = iCarService.findById(Integer.parseInt(txtCarId.getText()));
        car.setCarName(txtCarName.getText());
        car.setCarModelYear(Integer.parseInt(txtCarModelYear.getText()));
        car.setColor(txtColor.getText());
        car.setCapacity(Integer.parseInt(txtCapacity.getText()));
        car.setRentPrice(Double.parseDouble(txtRentPrice.getText()));
        car.setStatus(CarStatus.valueOf(statusCombobox.getValue())); // Adjust if necessary
        car.setProducerID(iCarProducerService.findByProducerName(productCombobox.getValue())); // Assuming producerID is CarProducer

        iCarService.update(car);
        refreshTable();
        clearFields();
    }

    @FXML
    private void deleteOnAction() {
        Integer carId = Integer.parseInt(txtCarId.getText());
        iCarService.delete(carId);
        refreshTable();
        clearFields();
    }

    @FXML
    private void cancelOnAction() {
        clearFields();
    }

    private void clearFields() {
        txtCarId.clear();
        txtCarName.clear();
        txtCarModelYear.clear();
        txtColor.clear();
        txtCapacity.clear();
        txtRentPrice.clear();
        productCombobox.getSelectionModel().clearSelection();
        statusCombobox.getSelectionModel().clearSelection();
    }
}
