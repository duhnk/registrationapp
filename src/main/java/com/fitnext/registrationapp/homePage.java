package com.fitnext.registrationapp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;

public class homePage {

    private Stage stage;
    private Scene scene;
    private Parent root;



    public void switchToScene(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("bmi-page.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToScene1(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("resource-page.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene2(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("log-in.fxml"));
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
