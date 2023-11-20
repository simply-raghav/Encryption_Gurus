package com.example.demo;

import animatefx.animation.BounceIn;
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
import javafx.stage.Stage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.io.IOException;

public class RegisterApp extends Application{
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Hello!");

//        new FadeIn(root).play();
        new BounceIn(root).play();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private PasswordField newPasswordField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private Label emailValidateLabel;
    @FXML
    private Label passwordValidateLabel;
    private Parent root;
    private Stage stage;
    private Scene scene;



    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);
    public void openLoginScene(ActionEvent event) throws Exception{
        boolean emailFlag = false, passFlag = false;

        String newEmail = emailField.getText();
        if(!pattern.matcher(newEmail).matches()){
            emailField.setText("");
            emailValidateLabel.setVisible(true);
            emailFlag = false;
        }else{
            emailValidateLabel.setVisible(false);
            emailFlag = true;
        }
        if(newPasswordField.getText().length() <= 8){
            passwordValidateLabel.setVisible(true);
            passFlag = false;
            newPasswordField.setText("");
        }else{
            passwordValidateLabel.setVisible(false);
            passFlag = true;
        }

        if(emailFlag  && passFlag){

            System.out.println(usernameField.getText());
            System.out.println(emailField.getText());
            System.out.println(newPasswordField.getText());

            root = FXMLLoader.load(getClass().getResource("login.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            new BounceIn(root).play();
            stage.show();
        }else{
            System.out.println("Enter all Elements");
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}

