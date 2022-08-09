package com.fitnext.registrationapp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static com.fitnext.registrationapp.connectionDB.getConnection;

public class LogInController implements Initializable{

    public Label userNameLabel;
    public Label passwordLabel;
    public Button loginBtn;
    public Button signUpBtn;
    public TextField userEmail;
    public Button cancelBtn;
    public PasswordField password;
    public Label signFailedtxt;

    public LogInController() throws SQLException, ClassNotFoundException {

    }

    public void switchToScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("registration-view.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        Platform.exit();
        System.out.println("Cancelling");
    }

    public void login(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        String userEmail = this.userEmail.getText();
        String password = this.password.getText();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE email = '" + userEmail + "'");
        if (resultSet.next()) {
            if (resultSet.getString("password").equals(password)) {
                System.out.println("Login Successful");
                Parent root = FXMLLoader.load(getClass().getResource("home-page.fxml"));

                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                System.out.println("Login Failed");
                signFailedtxt.setText("Login Failed");
            }
        } else {
            System.out.println("Login Failed");
            signFailedtxt.setText("Login Failed");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginBtn.setOnAction(event -> {
            try {
                login(event);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        try {
            Connection conn = getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
