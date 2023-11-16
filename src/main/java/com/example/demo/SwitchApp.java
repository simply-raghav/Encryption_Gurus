package com.example.demo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SwitchApp extends Application {

    public void start(Stage stage) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
//            e.printStackTrace();
            System.out.println("Error");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
