package com.example.demo;
import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class DecryptApp extends Application {

    public static Parent DEC_root;
    @Override
    public void start(Stage stage){
        try{
            DEC_root = FXMLLoader.load(getClass().getResource("decrypt.fxml"));
            Scene scene = new Scene(DEC_root);
            stage.setTitle("Hello!");
            new FadeIn(DEC_root).play();
            stage.centerOnScreen();


            stage.setScene(scene);
            stage.setTitle("Secure Vault");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("Images/logo.png")));
            stage.setResizable(false);


            stage.show();
        }catch (Exception e){
            System.out.println("Error in APp");
            System.out.println(e.toString());
        }


    }

    public static void main(String[] args) {
        launch();
    }

}
