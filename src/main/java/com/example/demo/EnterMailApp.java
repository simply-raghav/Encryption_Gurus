package com.example.demo;

import animatefx.animation.FadeIn;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.awt.*;

public class EnterMailApp extends Application {

    public void start(Stage stage) throws Exception{
        try{
            Parent root = FXMLLoader.load(getClass().getResource("enterEMail.fxml"));
            Scene scene = new Scene(root);
            new FadeIn(root).play();
            stage.centerOnScreen();

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
    public TextField mailTextField;

    public boolean isValidMail(String mail){
        String regex = "^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return mail.matches(regex);
    }

    public void sendMail(){
        String mail = mailTextField.getText();
        if(isValidMail(mail)){
            System.out.println("Sending Mail to: " + mail);
        }else{
            System.out.println("Invalid Email");
        }
    }
}
