package com.fitnext.registrationapp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.Statement;

public class RegistrationController implements Initializable {
    @FXML
    public ImageView imageTest;
    @FXML
   private Button btnRegister;
    @FXML
    private Button btnCancel;
    @FXML
    public TextField firstNameText;
    @FXML
    public TextField lastNameText;
    @FXML
    public TextField emailText;
    @FXML
    public PasswordField passwordText;
    @FXML
    public PasswordField confirmPasswordText;
    @FXML
    public TextField phoneText;
    @FXML
    public TextField addressText;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private Label confirmPasswordMessageLabel;



    public void register(ActionEvent actionEvent) {
        registrationMessageLabel.setText("User has been registered successfully!");
        registerUser();
    }
    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
        Platform.exit();
        System.out.println("Cancelling");
    }
    public void registerUser()
    {
        if(passwordText.getText().equals(confirmPasswordText.getText()))
        {
            registrationMessageLabel.setText("Registration Successful");
        }
        else
        {
            confirmPasswordMessageLabel.setText("Passwords do not match");
        }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}