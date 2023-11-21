package com.example.demo;

import animatefx.animation.BounceIn;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LogOutApp extends Application {

    @FXML
    private Button powerButton;

    @Override
    public void start(Stage stage) throws IOException {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("logout.fxml"));
            Scene scene = new Scene(root);
            new BounceIn(root).play();
            stage.setScene(scene);

            stage.setTitle("Secure Vault");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("Images/logo.png")));
            stage.setResizable(false);

            stage.show();
        }catch (Exception e){
            System.out.println(e.toString());
        }


    }

    @FXML
    private Parent root;
    private Stage stage;
    private Scene scene;

    public void openLogin(ActionEvent event){
        try{
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            new BounceIn(root).play();
            stage.show();
        }catch(Exception e){
            System.out.println("Decrypt");
            System.out.println(e.toString());
        }

        System.out.println("Login");
    }

    public static void main(String[] args) {
        launch();
    }
}
