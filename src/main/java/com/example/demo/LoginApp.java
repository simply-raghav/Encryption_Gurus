package com.example.demo;

import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApp extends Application {
    private LogOutApp newObj = new LogOutApp();
    private Parent root;
    private Scene scene;
    private Stage stage;

    public void start(Stage stage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        scene = new Scene(root);
        new FadeIn(root).play();

        stage.setTitle("Secure Vault");
//        stage.getIcons().add(new Image(getClass().getResourceAsStream("Images/logo.png")));
        stage.getIcons().add(newObj.icon);
        stage.setResizable(false);
        stage.centerOnScreen();


        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private Label usernameLoginLabel;
    @FXML
    private Label passwordLoginLabel;

    public void homePageScene(ActionEvent event) throws Exception{
        boolean usrFlag = false, pwdFlag = false;
        String usrName = usernameField.getText();
        String pwd = passwordField.getText();


        if(usrName.isEmpty()){
            usernameLoginLabel.setVisible(true);
            usernameField.setText("");
            usrFlag = false;
        }else{
            usrFlag = true;
            usernameLoginLabel.setVisible(false);
        }
        if(pwd.isEmpty()){
            passwordLoginLabel.setVisible(true);
            passwordField.setText("");
            pwdFlag = false;
        }else{
            pwdFlag = true;
            passwordLoginLabel.setVisible(false);
        }

        boolean chkFlag = true;
        login_data_connectivity str=new login_data_connectivity(usrName,pwd);
        chkFlag = str.checkdata(event);

        if(usrFlag && pwdFlag && chkFlag){


            System.out.println(usernameField.getText());
            System.out.println(passwordField.getText());

            LogOutApp obj = new LogOutApp();
            if(obj.mainStage != null)
                obj.mainStage.close();

            root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            new FadeIn(root).play();
            stage.show();
        }else{
//            stage.show();
            stage.toFront();
        }

    }

    public void registerScene(ActionEvent event) throws Exception{
        root = FXMLLoader.load(getClass().getResource("register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        new BounceIn(root).play();
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

//        calling the register page
        new RegisterApp().sendOTP(event);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
