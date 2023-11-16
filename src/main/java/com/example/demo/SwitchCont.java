package com.example.demo;


import java.io.IOException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SwitchCont {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene1(ActionEvent event) throws Exception{
        try{
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene1.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println("Error in Scene1");
            System.out.println(e.toString());
        }
    }

    public void switchToScene2(ActionEvent event)throws Exception{
       try{
           root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
       }catch(Exception e){
           System.out.println("Error in Scene2");
       }
    }
}
