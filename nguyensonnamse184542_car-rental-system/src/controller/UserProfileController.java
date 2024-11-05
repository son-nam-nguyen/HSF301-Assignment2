package controller;

import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import pojo.Customer;
import repository.CustomerRepository;
import repository.ICustomerRepository;

public class UserProfileController implements Initializable {

    private ICustomerRepository iCustomerRepository;
    private Customer currentCustomer;

    @FXML
    private TextField txtCustomerName, txtMobile, txtEmail, txtIdentityCard, txtLicenceNumber;

    @FXML
    private DatePicker dpDateOfBirth, dpLicenceDate;

    @FXML
    private Button btnUpdate, btnCancel;

    public UserProfileController() {
        iCustomerRepository = new CustomerRepository("JPAs");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentCustomer = LoginController.getLoggedInCustomer(); 
        if (currentCustomer != null) {
            loadUserProfile(currentCustomer);
        } else {
            showAlert("Error", "No customer data found for the logged-in user.", AlertType.ERROR);
        }
    }

    private void loadUserProfile(Customer customer) {
        txtCustomerName.setText(customer.getCustomerName());
        txtMobile.setText(customer.getMobile());
        txtEmail.setText(customer.getEmail());
        txtIdentityCard.setText(customer.getIdentityCard());
        txtLicenceNumber.setText(customer.getLicenceNumber());
        if (customer.getBirthday() != null) {
            dpDateOfBirth.setValue(customer.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        if (customer.getLicenceDate() != null) {
            dpLicenceDate.setValue(customer.getLicenceDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
    }

    private boolean validateInput() {
        String email = txtEmail.getText();
        String mobile = txtMobile.getText();
        String identityCard = txtIdentityCard.getText();

        Customer existingCustomerByEmail = iCustomerRepository.findByEmail(email);
        if (existingCustomerByEmail != null && !existingCustomerByEmail.getCustomerID().equals(currentCustomer.getCustomerID())) {
            showAlert("Validation Error", "Email is already in use.", AlertType.WARNING);
            return false;
        }

        Customer existingCustomerByMobile = iCustomerRepository.findByMobile(mobile);
        if (existingCustomerByMobile != null && !existingCustomerByMobile.getCustomerID().equals(currentCustomer.getCustomerID())) {
            showAlert("Validation Error", "Mobile number is already in use.", AlertType.WARNING);
            return false;
        }

        Customer existingCustomerByIdentityCard = iCustomerRepository.findByIdentityCard(identityCard);
        if (existingCustomerByIdentityCard != null && !existingCustomerByIdentityCard.getCustomerID().equals(currentCustomer.getCustomerID())) {
            showAlert("Validation Error", "Identity card number is already in use.", AlertType.WARNING);
            return false;
        }

        return true;
    }

    @FXML
    public void updateOnAction() {
        if (currentCustomer == null) {
            showAlert("Error", "Unable to update profile. No customer data loaded.", AlertType.ERROR);
            return;
        }

        if (validateInput()) {
            currentCustomer.setCustomerName(txtCustomerName.getText());
            currentCustomer.setMobile(txtMobile.getText());
            currentCustomer.setEmail(txtEmail.getText());
            currentCustomer.setIdentityCard(txtIdentityCard.getText());
            currentCustomer.setLicenceNumber(txtLicenceNumber.getText());

            if (dpDateOfBirth.getValue() != null) {
                currentCustomer.setBirthday(java.sql.Date.valueOf(dpDateOfBirth.getValue()));
            }
            if (dpLicenceDate.getValue() != null) {
                currentCustomer.setLicenceDate(java.sql.Date.valueOf(dpLicenceDate.getValue()));
            }

            boolean isUpdated = iCustomerRepository.updateCustomer(currentCustomer);
            if (isUpdated) {
                showAlert("Success", "Profile updated successfully.", AlertType.INFORMATION);
            } else {
                showAlert("Error", "Profile update failed.", AlertType.ERROR);
            }
        }
    }

    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void cancelOnAction() {
        Platform.exit();
    }
}
