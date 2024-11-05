package controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class ViewRentalController {
	@FXML
	private Button btnCarManagement;
	@FXML
	private Button btnCustomer;
	@FXML
	private Button btnRental;
	@FXML
	private Button btnTransaction;
	@FXML
	private Button btnUser;
	@FXML
	private Button btnCancel;
	
	private String role;

	public void setRole(String role) {
        this.role = role;

        switch (this.role.trim().toUpperCase()) {
            case "ADMIN": // Admin
                this.btnCarManagement.setDisable(false);
                this.btnCustomer.setDisable(false);
                this.btnRental.setDisable(false);
                this.btnTransaction.setDisable(true);
                this.btnUser.setDisable(true);
                this.btnCancel.setDisable(false);

                break;
            case "CUSTOMER": // Staff
            	 this.btnCarManagement.setDisable(true);
                 this.btnCustomer.setDisable(true);
                 this.btnRental.setDisable(true);
                this.btnTransaction.setDisable(false);
                this.btnUser.setDisable(false);
                this.btnCancel.setDisable(false);

                break;
            default:
                showAlert("Lỗi phân quyền", "Vai trò không xác định!" + this.role);
                break;
        }
    }
	
	@FXML public void cancelOnAction() throws IOException {
		Platform.exit();
	}
	@FXML 
	public void rentalOnAction() throws IOException {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/RentalManagement.fxml"));
	    AnchorPane root = loader.load();
	    
	    Stage stage = (Stage) btnRental.getScene().getWindow();
	    
	    stage.setScene(new Scene(root));
	    stage.show();
	}
	@FXML 
	public void customerOnAction() throws IOException {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/CustomerManagement.fxml")); // Đường dẫn đến rental.fxml
		    Parent root = loader.load();
		    
		    // Lấy stage hiện tại từ một nút bất kỳ trên scene hiện tại
		    Stage stage = (Stage) btnRental.getScene().getWindow();
		    
		    // Đặt scene mới từ FXML mới
		    stage.setScene(new Scene(root));
		    stage.show();
	}
	@FXML 
	public void transactionOnAction() throws IOException {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/TransactionHistory.fxml")); // Đường dẫn đến rental.fxml
		    Parent root = loader.load();
		    
		    // Lấy stage hiện tại từ một nút bất kỳ trên scene hiện tại
		    Stage stage = (Stage) btnRental.getScene().getWindow();
		    
		    // Đặt scene mới từ FXML mới
		    stage.setScene(new Scene(root));
		    stage.show();
	}
	@FXML 
	public void userOnAction() throws IOException {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/UserProfile.fxml")); // Đường dẫn đến rental.fxml
		    Parent root = loader.load();
		    
		    // Lấy stage hiện tại từ một nút bất kỳ trên scene hiện tại
		    Stage stage = (Stage) btnRental.getScene().getWindow();
		    
		    // Đặt scene mới từ FXML mới
		    stage.setScene(new Scene(root));
		    stage.show();
	}
	@FXML 
	public void carOnAction() throws IOException {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/CarManagement.fxml")); // Đường dẫn đến rental.fxml
		    Parent root = loader.load();
		    
		    // Lấy stage hiện tại từ một nút bất kỳ trên scene hiện tại
		    Stage stage = (Stage) btnRental.getScene().getWindow();
		    
		    // Đặt scene mới từ FXML mới
		    stage.setScene(new Scene(root));
		    stage.show();
	}
	protected void showAlert(String title, String message) {
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();
	}
}
