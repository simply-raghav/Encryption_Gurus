package com.example.demo;
//
import animatefx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class EncryptApp extends Application {

public static Parent Enc_root;
    @Override
    public void start(Stage stage) throws IOException {
        try{
            Enc_root = FXMLLoader.load(getClass().getResource("encrypt.fxml"));
            Scene scene = new Scene(Enc_root);
            new FadeIn(Enc_root).play();
            stage.setScene(scene);


            stage.setTitle("Secure Vault");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("Images/logo.png")));
            stage.setResizable(false);


            stage.show();

        }catch (Exception e){
            System.out.println(e.toString());
        }


    }

    public static void main(String[] args) {
        launch();
    }
}