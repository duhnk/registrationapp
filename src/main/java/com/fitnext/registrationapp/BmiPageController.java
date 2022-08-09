package com.fitnext.registrationapp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;



public class BmiPageController {

    public Button results;
    public TextField weightText;
    public TextField heightText;
    public TextArea bmiText;
    public Button forwardBtn;
    public Button bmiBtn;
    public TextArea resultsTextOut;
    public TextField enterBMIText;


    public void switchToScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home-page.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("resource-page.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void getResults(ActionEvent actionEvent) throws IOException, InterruptedException {
        BMI();
    }
    public void getBmi(ActionEvent actionEvent) throws IOException, InterruptedException {

        imperialPounds();

    }

    public String imperialPounds() throws IOException, InterruptedException {
        System.out.println("Imperial Pounds");
        String weight = String.valueOf(weightText.getText());
        String height = String.valueOf(heightText.getText());
        String result = "";

        String url = "https://body-mass-index-bmi-calculator.p.rapidapi.com/imperial?weight="+weight+"&height="+height;
        String request = "GET";
        String response = "application/json";
        String header = "X-RapidAPI-Key";
        String headerValue = "daa95d7842msh3f4ff39a546c2eep12d517jsn0d5a6b775462";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .setHeader(header, headerValue)
                .setHeader("Content-Type", response)
                .setHeader("Accept", response)
                .build();
        HttpResponse<String> response1 = client.send(request1, HttpResponse.BodyHandlers.ofString());

        System.out.println(response1.body());
        JSONObject jsonObject = new JSONObject(response1.body());
        result = jsonObject.get("bmi").toString();

        bmiText.setText(result);
        return result;


    }
    public void BMI() throws IOException, InterruptedException {

        System.out.println("BMI");

        String bmi = String.valueOf(enterBMIText.getText());
        String url = "https://body-mass-index-bmi-calculator.p.rapidapi.com/weight-category?bmi=" + bmi;
        ;
        String results = "";
        String request = "GET";
        String response = "application/json";
        String header = "X-RapidAPI-Key";
        String headerValue = "daa95d7842msh3f4ff39a546c2eep12d517jsn0d5a6b775462";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .setHeader(header, headerValue)
                .setHeader("Content-Type", response)
                .setHeader("Accept", response)
                .build();
        HttpResponse<String> response1 = client.send(request1, HttpResponse.BodyHandlers.ofString());
        System.out.println(response1.body());
        JSONObject jsonObject = new JSONObject(response1.body());
        results = jsonObject.get("weightCategory").toString();


        resultsTextOut.setText(results);


    }
    }
