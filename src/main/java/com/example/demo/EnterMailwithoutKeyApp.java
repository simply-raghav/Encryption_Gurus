package com.example.demo;

import animatefx.animation.FadeIn;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.awt.*;

public class EnterMailwithoutKeyApp extends Application {

    public void start(Stage stage) throws Exception{
        try{
            Parent root = FXMLLoader.load(getClass().getResource("enterEmailwithoutKey.fxml"));
            Scene scene = new Scene(root);
            new FadeIn(root).play();
            stage.centerOnScreen();

            stage.setScene(scene);

            stage.setTitle("Secure Vault");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("Images/logo.png")));
            stage.setResizable(false);

            stage.show();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    @FXML
    public TextField mailTextField;

    public boolean isValidMail(String mail){
        String regex = "^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return mail.matches(regex);
    }

    public void sendMail(){
        String mail = mailTextField.getText();
        if(isValidMail(mail)){
//            System.out.println("Sending Mail to: " + mail);
            /*********************************************************************/
            //calling class attachment mail
            Attachment_mail sending_data=new Attachment_mail();
            /****************************/
            for(var file : HistoryApp.selectedHistoryFiles){
                sending_data.send_data_to_mail(mail, file.getPath());
                System.out.println("Sending file: " + file.getPath() + "To :" + mail);
            }
            System.out.println("Mail send");
            System.out.println(mail);

        }else{
            System.out.println("Invalid Email");
        }
    }
}
