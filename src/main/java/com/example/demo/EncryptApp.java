package com.example.demo;
//
import animatefx.animation.BounceIn;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

public class EncryptApp extends Application {

    Popup historyPopup = new Popup();

    @FXML
    private Button showHistoryButton;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("encrypt.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Hello!");

            // Show the historyPopup when the button is clicked
//            historyPopup.show(stage);


//        historyPopup.setAutoHide(true);

        // action event
//        EventHandler<ActionEvent> event =
//                new EventHandler<ActionEvent>() {
//                    public void handle(ActionEvent e)
//                    {
//                        System.out.println("History2");
//                        if (!historyPopup.isShowing())
//                            historyPopup.show(stage);
//                    }
//                };
//
//
//        showHistoryButton.setOnAction(event);

//        new FadeIn(root).play();
            new BounceIn(root).play();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}