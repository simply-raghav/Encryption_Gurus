package com.example.demo;

import animatefx.animation.BounceIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageApp extends Application {
    private LogOutApp newObj = new LogOutApp();
    public void start(Stage stage) throws IOException {
       try{
           Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
           Scene scene = new Scene(root);
           stage.setScene(scene);
           new BounceIn(root).play();

           stage.setTitle("Secure Vault");
//           stage.getIcons().add(new Image(getClass().getResourceAsStream("Images/logo.png")));
           stage.getIcons().add(newObj.icon);
           stage.setResizable(false);


           stage.show();
       }catch (Exception e){
           System.out.println("HomePAge Error");
           System.out.println(e.toString());
       }

    }

    public static void main(String[] args) {
        launch(args);
    }
}

