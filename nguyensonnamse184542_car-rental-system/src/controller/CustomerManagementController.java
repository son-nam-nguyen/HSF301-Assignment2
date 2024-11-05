package controller;


import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.Customer;
import service.CustomerService;
import service.ICustomerService;

public class CustomerManagementController {
    private ICustomerService iCustomerService;

    @FXML
    private TextField txtCustomerId;
    
    @FXML
    private TextField txtCustomerName;
    
    @FXML
    private TextField txtMobile;
    
    @FXML
    private TextField txtEmail;
    
    @FXML
    private TableView<Customer> tableCustomers;
    
    @FXML
    private TableColumn<Customer, String> colCustomerId;
    
    @FXML
    private TableColumn<Customer, String> colCustomerName;
    
    @FXML
    private TableColumn<Customer, String> colMobile;
    
    @FXML
    private TableColumn<Customer, String> colEmail;

    private ObservableList<Customer> customerList;

    @FXML
    public void initialize() {
        // Liên kết cột TableView với thuộc tính từ lớp Customer
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Đặt danh sách khách hàng cho TableView
        tableCustomers.setItems(customerList);
        tableCustomers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
            @Override
            public void changed(ObservableValue<? extends Customer> observableValue, Customer oldValue, Customer selectedCustomer) {
                if (selectedCustomer != null) {
                    showCustomer(selectedCustomer);
                }
            }
        });
    }

    public CustomerManagementController() {
        iCustomerService = new CustomerService("JPAs");
        customerList = FXCollections.observableArrayList(iCustomerService.getAllCustomers());
    }
    

    

    // Phương thức xử lý khi bấm nút "Update"
    @FXML
    void updateOnAction(ActionEvent event) {
        Customer selectedCustomer = tableCustomers.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null && validateInput()) {
            selectedCustomer.setCustomerName(txtCustomerName.getText());
            selectedCustomer.setMobile(txtMobile.getText());
            selectedCustomer.setEmail(txtEmail.getText());
            iCustomerService.update(selectedCustomer);
            refreshCustomerList();
            clearFields();
        } else {
            showAlert("No Customer Selected", "Please select a customer to update.");
        }
    }

    // Phương thức xử lý khi bấm nút "Delete"
    @FXML
    void searchOnAction(ActionEvent event) {
        String customerName = txtCustomerName.getText();
        if (customerName.isEmpty()) {
            showAlert("Invalid Input", "Please enter a customer name to search.");
            return;
        }

        // Search for customers by name
        List<Customer> searchResults = iCustomerService.findByCustomerName(customerName);
        if (!searchResults.isEmpty()) {
            customerList.setAll(searchResults);  // Update TableView with search results
        } else {
            showAlert("No Results", "No customers found with the name: " + customerName);
        }
    }


    // Phương thức xử lý khi bấm nút "Cancel"
    @FXML
    void cancelOnAction(ActionEvent event) {
        clearFields();
    }

    // Xóa trắng các trường nhập liệu
    private void clearFields() {
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtMobile.clear();
        txtEmail.clear();
    }

    // Kiểm tra tính hợp lệ của dữ liệu đầu vào
    private boolean validateInput() {
        if (txtCustomerName.getText().isEmpty() || txtMobile.getText().isEmpty() || txtEmail.getText().isEmpty()) {
            showAlert("Invalid Input", "Please fill out all fields.");
            return false;
        }

        if (!txtEmail.getText().matches("^(.+)@(.+)$")) {
            showAlert("Invalid Email", "Please enter a valid email address.");
            return false;
        }

        if (!txtMobile.getText().matches("\\d{10}")) {
            showAlert("Invalid Mobile", "Please enter a valid 10-digit mobile number.");
            return false;
        }

        return true;
    }

    // Hiển thị thông báo
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    // Hiển thị khách hàng
    private void showCustomer(Customer customer) {
        txtCustomerId.setText(String.valueOf(customer.getCustomerID()));
        txtCustomerName.setText(customer.getCustomerName());
        txtMobile.setText(customer.getMobile());
        txtEmail.setText(customer.getEmail());
    }

    // Làm mới danh sách khách hàng
    private void refreshCustomerList() {
        customerList.setAll(iCustomerService.getAllCustomers());
        tableCustomers.refresh();
    }
}
