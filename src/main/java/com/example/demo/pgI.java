package com.example.demo;

import animatefx.animation.FadeIn;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class pgI extends Application {
    public void start(Stage stage){
//        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        new FadeIn(root).play();

        ProgressIndicator pi = new ProgressIndicator(0.30);
        pi.setProgress(-1);
        root.setCenter(pi);

        stage.setTitle("Secure Vault");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("Images/logo.png")));
        stage.setResizable(false);

        stage.initStyle(StageStyle.UNDECORATED);
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished( event -> stage.close() );
        delay.play();
        stage.setScene(scene);
        stage.show();
    }
}
