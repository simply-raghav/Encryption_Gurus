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

import javax.mail.MessagingException;
import javax.swing.*;
import java.io.UnsupportedEncodingException;
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
    private TextField OTPField;
    @FXML
    private TextField emailField;
    @FXML
    private Label emailValidateLabel;
    @FXML
    private Label passwordValidateLabel;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private String otp_String_sender;

    public void openLoginScene(ActionEvent event) throws Exception{
        boolean passFlag = false;


        if(newPasswordField.getText().length() <= 8){
            passwordValidateLabel.setVisible(true);
            passFlag = false;
            newPasswordField.setText("");
        }else{
            passwordValidateLabel.setVisible(false);
            passFlag = true;
        }

        if(passFlag){

            // from here im getting the name email and password
            // one is for otp and one for the new password
            String otp=OTPField.getText();
            String newpassword=newPasswordField.getText();



            System.out.println(OTPField.getText()); //this is our opt for now
//            System.out.println(emailField.getText());
            System.out.println(newPasswordField.getText());

            //calling a constructor of change_password
            if(otp_String_sender.equals(otp)){
                change_password chg=new change_password();
                chg.changePassword(newpassword);
                System.out.println("Successfully password Changed");
            }else{
                JOptionPane.showMessageDialog(null,"Incorrect OTP...");
            }


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

    public void sendOTP(ActionEvent event) throws MessagingException, UnsupportedEncodingException {
        //Isse durr rahe danger for the email purpose

        int min=1000;
        int max=10000;

        double otpsender=Math.random()*(max-min+1)+min;
        int realotp=(int)otpsender;

        // What is your OTP
        System.out.println(realotp);

        otp_String_sender= Integer.toString(realotp);
        // calling constructor of Email
        Email mail=new Email();
        mail.setupServerProperties();
        mail.draftEmail(otp_String_sender);
        mail.sendEmail();
        System.out.println("OTP has been sent");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

