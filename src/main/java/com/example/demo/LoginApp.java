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




    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        new FadeIn(root).play();

        stage.setTitle("Secure Vault");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("Images/logo.png")));
        stage.setResizable(false);

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
    private Parent root;
    private Stage stage;
    private Scene scene;
    public void loginSubmit(ActionEvent event) throws Exception{
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

        if(usrFlag && pwdFlag){
            login_data_connectivity str=new login_data_connectivity(usrName,pwd);
            str.checkdata(event);

            System.out.println(usernameField.getText());
            System.out.println(passwordField.getText());

            LogOutApp obj = new LogOutApp();
            obj.mainStage.close();
////            ActionEvent event = new ActionEvent();
//            obj.closeMainStage(event);
//            LogOutApp.getInstance().closeMainStage(event);
//            new LogOutApp().closeMainStage(event);
        }

    }

    public void registerScene(ActionEvent event) throws Exception{
        root = FXMLLoader.load(getClass().getResource("register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        new BounceIn(root).play();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
