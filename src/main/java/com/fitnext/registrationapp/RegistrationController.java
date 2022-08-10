package com.fitnext.registrationapp;
import java.io.IOException;
import java.sql.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.Statement;

import static com.fitnext.registrationapp.connectionDB.getConnection;



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


    public void switchToScene(ActionEvent event) throws IOException {
        AnchorPane loginScreen = null;
        loginScreen = FXMLLoader.load(getClass().getResource("log-in.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loginScreen);
        stage.setScene(scene);
        stage.show();
    }
    public void register(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {


        registrationMessageLabel.setText("User has been registered successfully!");
        if(registerUser() == true){
            registrationMessageLabel.setText("User has been registered successfully!");
            user = addUserToDatabase(phoneText.getText(), emailText.getText(), usernameText.getText(), passwordText.getText());
            switchToScene(actionEvent);
        }
        else{
            registrationMessageLabel.setText("User has not been registered successfully!");
        }



    }
    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
        Platform.exit();
        System.out.println("Cancelling");
    }

    public boolean registerUser() throws SQLException, ClassNotFoundException {
        boolean result = true;
        String email = emailText.getText();
        String password = String.valueOf(passwordText.getText());
        String confirmPassword = String.valueOf(confirmPasswordText.getText());
        String phone = phoneText.getText();
        String username = usernameText.getText();
        if ( email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phone.isEmpty() || username.isEmpty()) {
            registrationMessageLabel.setText("Please fill in all fields");
            result = false;
        } else if (!password.equals(confirmPassword)) {
            confirmPasswordMessageLabel.setText("Passwords do not match");
        }
        if (passwordText.getText().equals(confirmPasswordText.getText())) {

            registrationMessageLabel.setText("Password and Confirm Password do match");

        } else {
            confirmPasswordMessageLabel.setText("Passwords do not match");
            result = false;
        }


return result;
    }

    public User user;

    private User addUserToDatabase( String phone, String email, String username, String password) throws SQLException, ClassNotFoundException {

                Connection connection = getConnection();

                Statement stmt = connection.createStatement();
                String sql = "INSERT INTO users (username , password, phone, email)VALUES  ( ? ,? ,? ,? )";
                PreparedStatement preparedStmt = connection.prepareStatement(sql);
                preparedStmt.setString(1, username);
                preparedStmt.setString(2, password);
                preparedStmt.setString(3, phone);
                preparedStmt.setString(4, email);




                int addedRows = preparedStmt.executeUpdate();
                if (addedRows > 0) {
                    user = new User();
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

