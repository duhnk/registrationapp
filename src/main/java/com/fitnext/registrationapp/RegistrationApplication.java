package com.fitnext.registrationapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class RegistrationApplication extends Application {

    private Scene scene;
    private Parent root;
    @Override
    public void start(Stage stage) {
        try {
             root = FXMLLoader.load(getClass().getResource("log-in.fxml"));

             scene = new Scene(root, 520, 600);
            stage.setTitle("Welcome to FitNext!");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    public static void main(String[] args) {
        launch();
    }
}