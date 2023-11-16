package com.example.demo;

import animatefx.animation.BounceIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageCont {
    private Stage stage;
    private Parent root;
    private Scene scene;
   @FXML
    public void Encrypt(ActionEvent event) throws IOException {
       try{
           root = FXMLLoader.load(getClass().getResource("encrypt.fxml"));
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           new BounceIn(root).play();
           stage.show();
       }catch(Exception e){
           System.out.println("Encrypt");
           System.out.println(e.toString());
       }
    }

    @FXML
    public void Decrypt(ActionEvent event)throws IOException{
        try{
            root = FXMLLoader.load(getClass().getResource("decrypt.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            new BounceIn(root).play();
            stage.show();
        }catch (Exception e){
            System.out.println("Decrypt");
            System.out.println(e.toString());
        }

    }
}

