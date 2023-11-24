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

    public Parent mainRoot;
    public Scene mainScene;
    public static Stage mainStage;
    public static Image icon = new Image(LogOutApp.class.getResourceAsStream("Images/logo.png"));
    @Override
    public void start(Stage mainStage) throws IOException {
        try{
            this.mainStage = mainStage;
            mainRoot = FXMLLoader.load(getClass().getResource("logout.fxml"));
            mainScene = new Scene(mainRoot);
            new BounceIn(mainRoot).play();
            mainStage.setScene(mainScene);

            mainStage.setTitle("Secure Vault");
//            mainStage.getIcons().add(new Image(getClass().getResourceAsStream("Images/logo.png")));
            mainStage.getIcons().add(icon);
            mainStage.setResizable(false);
            mainStage.centerOnScreen();

            mainStage.show();

        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

    private Parent root;
    private Stage stage;


    public void openLogin(ActionEvent event){

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            root = (Parent) loader.load();
            stage = new Stage();
            stage.getIcons().add(icon);
            stage.setTitle("Secure Vault");
            stage.setScene(new Scene(root));

            new BounceIn(root).play();
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();
        }catch(Exception e){
            System.out.println(e.toString());
        }

        System.out.println("Login");
    }

    public static void main(String[] args) {
        launch();
    }
}
