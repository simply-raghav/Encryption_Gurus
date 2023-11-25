package com.example.demo;

import animatefx.animation.FadeIn;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class EnterMailwithKeyApp extends Application {

    public void start(Stage stage) throws Exception{
        try{
            Parent root = FXMLLoader.load(getClass().getResource("enterEmailWithKey.fxml"));
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
    public TextField mailTextField, keyTextField;

    public boolean isValidMail(String mail){
        String regex = "^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return mail.matches(regex);
    }

//    public void sendMail(){
//        String mail = mailTextField.getText();
//        if(isValidMail(mail)){
////            System.out.println("Sending Mail to: " + mail);
//            /*********************************************************************/
//            //calling class attachment mail
//            Attachment_mail sending_data=new Attachment_mail();
//            /****************************/
//            for(var file : HistoryApp.selectedHistoryFiles){
//                File newFile;
//                newFile = previewEncryptedFile(file);
//
//                sending_data.send_data_to_mail(mail, file.getPath());
//                newFile.delete();
//                System.out.println("Sending file: " + file.getPath() + "To :" + mail);
//            }
//
//
//        }else{
//            System.out.println("Invalid Email");
//        }
//    }
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


        }else{
            System.out.println("Invalid Email");
        }
    }

//    private File previewEncryptedFile(History file) {
//        String key = showDialogBox();
//        if(key!=null){
//            try {
//                byte[] decryptedBytes = decryptFiles(key, file);
//
//                String ext = "";
//                String path = file.getPath();
//                int i = path.length()-1;
//                while(path.charAt(i)!='.'){
//                    ext = path.charAt(i--) + ext;
//                }
//                File newFile = new File("D:/temp_files/" + file.getName() + "." + ext);
//                Files.write(Path.of(newFile.getAbsolutePath()), decryptedBytes);
//                return newFile;
//            } catch (SQLException | ClassNotFoundException | IOException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//        return null;
//    }

//    public byte[] decryptFiles(String key, History file) throws SQLException, IOException, ClassNotFoundException {
//
//        /*********Connectivity to DB**********/
//        searching_algo_DB search=new searching_algo_DB();
//        boolean isDecrypted = false;
//        Path inputPath = Paths.get(file.getPath());
//        byte[] encryptedBytes = null;
//        byte[] decryptedBytes = null;
//
//        try {
//            encryptedBytes = Files.readAllBytes(inputPath);
//        }catch (IOException e) {
//            System.out.println(e);
//        }
//        try{
//            String AlgoMethod= search.algo_finder(file.getPath());            // To fetch Algo Method from encrypt_history DB
//
//            if (AlgoMethod.equalsIgnoreCase("AES/CBC/PKCS5Padding")) {
//                System.out.println("AES/Padding");
//                decryptedBytes = new AES_CBC_PKCS5Padding().decryptFile(encryptedBytes, key);
//            }
//
//            else if (AlgoMethod.equalsIgnoreCase("AES/ECB/NoPadding")) {
//                System.out.println("AES/NoPadding");
//                decryptedBytes = new AES_ECB_NoPadding().decryptFile(encryptedBytes, key);
//            }
//
//            else if (AlgoMethod.equalsIgnoreCase("DES/CBC/PKCS5Padding")) {
//                System.out.println("DES/CBC/Padding");
//                decryptedBytes = new DES_CBC_PKCS5Padding().decryptFile(encryptedBytes, key);
//            }
//
//            else if (AlgoMethod.equalsIgnoreCase("DES/ECB/PKCS5Padding")) {     // Isme Dikkt hai
//                System.out.println("DES/ECB/Padding");
//                decryptedBytes = new DES_ECB_PKCS5Padding().decryptFile(encryptedBytes, key);
//            }
//
//            else if (AlgoMethod.equalsIgnoreCase("Desede/CBC/PKCS5Padding")) {
//                System.out.println("Desede/Padding");
//                decryptedBytes = new Desede_CBC_PKCS5Padding().decryptFile(encryptedBytes, key);
//            }
//
//            isDecrypted = true;
//            /************************************/
//
//        }catch (Exception e){
//            System.out.println(e.toString());
//            JOptionPane.showMessageDialog(null, "Wrong Encryption Key", "ERROR", JOptionPane.ERROR_MESSAGE);
//            isDecrypted = false;
//        }
//        finally {
//            if(isDecrypted){
//                JOptionPane.showMessageDialog(null, "Files Decrypted Succesfully",
//                        "INFORMATION",
//                        JOptionPane.INFORMATION_MESSAGE);
//            }
//        }
//        return decryptedBytes;
//    }



//    private String showDialogBox() {
//        Dialog<String> dialog = new Dialog<>();
//        dialog.setTitle("Password Dialog Box");
//        dialog.setHeaderText("Enter Your Password:");
//
//        // Set the button types.
//        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
//
//        // Create the content grid.
//        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(20, 150, 10, 10));
//
//        // Create and add controls to the grid.
//        PasswordField passwordField = new PasswordField();
//        grid.add(new Label("Input:"), 0, 0);
//        grid.add(passwordField, 1, 0);
//
//        // Set the content of the dialog.
//        dialog.getDialogPane().setContent(grid);
//
//        // Request focus on the input field by default.
//        Platform.runLater(() -> passwordField.requestFocus());
//
//        // Convert the result to a string when the OK button is clicked.
//        dialog.setResultConverter(dialogButton -> {
//            if (dialogButton == ButtonType.OK) {
//                return passwordField.getText();
//            }
//            return null;
//        });
//
//        // Show the dialog and wait for the user's response.
//        String result = dialog.showAndWait().orElse(null);
//
//        if (result != null) {
//            System.out.println("User entered: " + result);
//        }
//        return result;
//    }

}

