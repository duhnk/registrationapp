package com.fitnext.registrationapp;
import java.sql.*;
import com.fitnext.registrationapp.assests.User;
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

import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.Statement;

;import static com.fitnext.registrationapp.connectionDB.getConnection;


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
    public TextField usernameText;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private Label confirmPasswordMessageLabel;


    public void register(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        registrationMessageLabel.setText("User has been registered successfully!");

        registerUser();

    }

    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
        Platform.exit();
        System.out.println("Cancelling");
    }

    public void registerUser() throws SQLException, ClassNotFoundException {
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();
        String email = emailText.getText();
        String password = String.valueOf(passwordText.getText());
        String confirmPassword = String.valueOf(confirmPasswordText.getText());
        String phone = phoneText.getText();
        String username = usernameText.getText();
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phone.isEmpty() || username.isEmpty()) {
            registrationMessageLabel.setText("Please fill in all fields");
        } else if (!password.equals(confirmPassword)) {
            confirmPasswordMessageLabel.setText("Passwords do not match");
        }
        user = addUserToDatabase(firstName, lastName, phone, email, username, password);
        if (user != null) {
            registrationMessageLabel.setText("User has been registered successfully!");
        } else {
            registrationMessageLabel.setText("Error creating user");
        }
        if (passwordText.getText().equals(confirmPasswordText.getText())) {
            registrationMessageLabel.setText("Registration Successful");
        } else {
            confirmPasswordMessageLabel.setText("Passwords do not match");
        }


    }

    public User user;

    private User addUserToDatabase(String name, String lastName, String phone, String email, String username, String password) throws SQLException, ClassNotFoundException {

                Connection connection = getConnection();






                Statement stmt = connection.createStatement();
                String sql = "INSERT INTO users (name,lastName, phone, email, username, password)VALUES  (  ?,?,? ,? ,? ,? )";
                PreparedStatement preparedStmt = connection.prepareStatement(sql);
                preparedStmt.setString(1, name);
                preparedStmt.setString(2, lastName);
                preparedStmt.setString(3, phone);
                preparedStmt.setString(4, email);
                preparedStmt.setString(5, username);
                preparedStmt.setString(6, password);


                int addedRows = preparedStmt.executeUpdate();
                if (addedRows > 0) {
                    user = new User();
                    user.name = name;
                    user.lastName = lastName;
                    user.phone = phone;
                    user.email = email;
                    user.username = username;
                    user.password = password;
                }
                stmt.close();
                connection.close();



        return user;

        }
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){

            try {
                getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }



}