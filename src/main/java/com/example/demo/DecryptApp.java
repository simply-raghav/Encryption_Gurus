package com.example.demo;
import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DecryptApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("decrypt.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Hello!");

//        new FadeIn(root).play();
        new BounceIn(root).play();

        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }

}
