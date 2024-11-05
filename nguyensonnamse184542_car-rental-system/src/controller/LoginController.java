package controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import pojo.Customer;
import repository.CustomerRepository;
import repository.ICustomerRepository;


public class LoginController {
private ICustomerRepository iCustomerRepository;
	
	public LoginController(){
		iCustomerRepository = new CustomerRepository("JPAs");
	}
	@FXML
	private TextField txtEmail;
	
	@FXML
	private PasswordField txtPassword;
	
private static Customer loggedInCustomer;
	
    @FXML
    public void loginOnAction() throws IOException {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        
        Customer account = iCustomerRepository.findByEmail(email);

        if (account == null || !account.getPassword().equals(password)) {
            showAlert("Invalid email or password.");
            return;
        }

        loggedInCustomer = account;

        Stage win = (Stage) txtEmail.getScene().getWindow();
        win.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/ViewRentalSystem.fxml"));
        Parent root = loader.load();
        
        ViewRentalController smController = loader.getController();
        smController.setRole(account.getAccount().getRole());
        
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
	public static Customer getLoggedInCustomer() {
        return loggedInCustomer;
    }
	private void showAlert(String message) {
	    Alert alert = new Alert(Alert.AlertType.ERROR);
	    alert.setTitle("Warning");
	    alert.setContentText(message);
	    alert.show();
	}
	@FXML public void CancelOnAction() throws IOException {
		Platform.exit();
	}
}
