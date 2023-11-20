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

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class LoginApp extends Application {
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Hello!");

//        new FadeIn(root).play();
        new BounceIn(root).play();
        stage.resizableProperty().setValue(Boolean.FALSE);
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



        //from here i get the password



            login_data_connectivity str=new login_data_connectivity(usrName,pwd);
            str.checkdata(event);

//            Statement stmt= con.createStatement();
//            //String q="select username,password from login_info where username='"+usrName+"'and password='"+pwd+"'";
//            String q = "select username, password from login_info where username='" + usrName + "' and password='" + pwd + "'";
//            ResultSet resultSet= stmt.executeQuery(q);
//            if(resultSet.next()){
//
//                System.out.println("Successful");
//                //JOptionPane.showMessageDialog(null,"successful");
//            }else{
//                JOptionPane.showMessageDialog(null,"Incorrect UserName or Password");
//                con.close();
//            }

//        }catch(Exception e ){
//            e.printStackTrace();
//            System.out.println("Invalid Password");
//        }

        System.out.println(usernameField.getText());
        System.out.println(passwordField.getText());

        if(usrName.isEmpty()){
            usernameLoginLabel.setVisible(true);
            usernameField.setText("");
        }else{
            usrFlag = true;
            usernameLoginLabel.setVisible(false);
        }
        if(pwd.isEmpty()){
            passwordLoginLabel.setVisible(true);
            passwordField.setText("");
        }else{
            pwdFlag = true;
            passwordLoginLabel.setVisible(false);
        }

//        if(usrFlag && pwdFlag){
//        root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
//                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//                scene = new Scene(root);
//                stage.setScene(scene);
//                new BounceIn(root).play();
//                stage.show();
//        }
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
