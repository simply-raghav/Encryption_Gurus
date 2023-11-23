package com.example.demo;

import animatefx.animation.BounceIn;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
//import java.awt.event.ActionEvent;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.security.PrivateKey;
import java.sql.*;
import java.util.EventObject;

public class login_data_connectivity {

 private static String usrName;
 private static String pwd;
    login_data_connectivity(String usrName,String pwd) throws SQLException, IOException {
 //this.con=con;
 this.usrName=usrName;
 this.pwd=pwd;
 }
    private Parent root;
    private Stage stage;
    private Scene scene;
    public boolean checkdata(ActionEvent event){
       boolean chkFlag = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/login";


            //Connection con;
            //DatabaseMetaData DriverManager = null;

            //String url="jdbc:mysql://localhost:3306/login";
            String username="root";
//            String password="0209rishi@Pa";
            String password="admin";
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement();
            //String q="select username,password from login_info where username='"+usrName+"'and password='"+pwd+"'";
            String q = "select username, password from login_info where username='" + usrName + "' and password='" + pwd + "'";
            ResultSet resultSet = stmt.executeQuery(q);
            if (resultSet.next()) {

                System.out.println("Successful");
                root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                new BounceIn(root).play();
                stage.show();

                // JOptionPane.showMessageDialog(null, "Correct");

                //JOptionPane.showMessageDialog(null,"successful");
                chkFlag = true;
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect UserName or Password");
                con.close();
                chkFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Invalid Password");
        }
        return chkFlag;
    }

        }
