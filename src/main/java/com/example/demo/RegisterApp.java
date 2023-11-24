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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        new FadeIn(root).play();

        stage.setTitle("Secure Vault");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("Images/logo.png")));
        stage.setResizable(false);
        stage.centerOnScreen();

        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private PasswordField newPasswordField;
    @FXML
    private TextField OTPField;
    @FXML
    private Label passwordValidateLabel;
    @FXML
    private Label OTPValidateLabel;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private static String otp_String_sender;


    @FXML
    private Button hide_Button;

    @FXML
    private TextField textField;

    @FXML
    private ImageView hideButtonImage;
    public void togglePassword(ActionEvent event) throws Exception {
        if (newPasswordField.isManaged()) {
            newPasswordField.setManaged(false);
            newPasswordField.setVisible(false);
            String password = newPasswordField.getText();
            textField.setText(password);
            hideButtonImage.setImage(new Image(getClass().getResourceAsStream("Images/eye2.png")));
        } else {
            newPasswordField.setVisible(true);
            newPasswordField.setManaged(true);
            textField.setText("");
            hideButtonImage.setImage(new Image(getClass().getResourceAsStream("Images/eyeHide.png")));
        }
    }

    public void sendOTP(ActionEvent event) throws MessagingException, UnsupportedEncodingException {
        //Isse durr rahe danger for the email purpose

        int min=1000;
        int max=10000;

        double otpsender=Math.random()*(max-min+1)+min;

        int realotp = (int)otpsender;
        this.otp_String_sender = Integer.toString(realotp);
        System.out.println(otp_String_sender);
        // calling constructor of Email
        try{
            Email mail=new Email();
            mail.setupServerProperties();
            mail.draftEmail(otp_String_sender);
            mail.sendEmail();
            System.out.println("OTP has been sent");
            // What is your OTP
            System.out.println(realotp);
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }


    public void openLoginSceneBeforeSubmit(ActionEvent event){
        try{
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            new BounceIn(root).play();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void openLoginSceneAfterSubmit(ActionEvent event) throws Exception{
        boolean passFlag = false, OTPFlag = false;


        if(newPasswordField.getText().length() <= 8){
            passwordValidateLabel.setVisible(true);     //  Show PAssowrd Validata Lable
            passFlag = false;
            newPasswordField.setText("");
        }else{
            passwordValidateLabel.setVisible(false);    // Hide PAssowrd Validata Lable
            passFlag = true;
        }

        if(OTPField.getText().length() > 0){
            OTPFlag = true;
            OTPValidateLabel.setVisible(false);
        }else{
            OTPValidateLabel.setVisible(true);
        }

        if(OTPFlag && passFlag){

            // from here im getting the name email and password
            // one is for otp and one for the new password
            String otp=OTPField.getText();
            String newpassword=newPasswordField.getText();


            OTPValidateLabel.setVisible(false);     // Hide OTP Validate Lable



            System.out.println(OTPField.getText()); //this is our opt for now
//            System.out.println(emailField.getText());

            System.out.println(newPasswordField.getText());

            //calling a constructor of change_password
            System.out.println(otp_String_sender+"Yeh value isme aayi hai");
            if(otp_String_sender.equals(otp)){
                change_password chg=new change_password();
                chg.changePassword(newpassword);

                System.out.println("Successfully password Changed");


                root = FXMLLoader.load(getClass().getResource("login.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                new BounceIn(root).play();
                stage.centerOnScreen();
                stage.setResizable(false);
                stage.show();


            }else{
                JOptionPane.showMessageDialog(null,"Incorrect OTP...");
            }

        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}

