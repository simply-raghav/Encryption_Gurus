package com.example.demo;

import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleAction;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HistoryApp extends Application implements Initializable {
    public void start(Stage stage) throws Exception{
        try{
            Parent root = FXMLLoader.load(getClass().getResource("history.fxml"));
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
    private Button encryptButton, decryptButton;
    public void showEncryptedHistory(ActionEvent event){
        encryptButton.setStyle("-fx-background-color: F46036; -fx-text-fill: FFF; -fx-background-radius: 10");
        decryptButton.setStyle("-fx-background-color: FFF; -fx-text-fill: F46036; -fx-background-radius: 10");
        System.out.println("Encrypt");

        //set All elements from Encrypt files History to history-Table
        historyTable.setItems(encryptHistoryList);
    }
    public void showDecryptedHistory(ActionEvent event){
        decryptButton.setStyle("-fx-background-color: F46036; -fx-text-fill: FFF; -fx-background-radius: 10");
        encryptButton.setStyle("-fx-background-color: FFF; -fx-text-fill: F46036; -fx-background-radius: 10");
        System.out.println("Decrypt");

        //set All elements from Decrypt files History to history-Table
        historyTable.setItems(decryptHistoryList);
    }

    public static void main(String[] args) {
        launch(args);
    }



    @FXML
    private TableColumn<History, Integer> sno = new TableColumn<>("S.No.");;
    @FXML
    private TableColumn<History, String> path;
    @FXML
    private TableColumn<History, String> name;
    @FXML
    private TableColumn<History, String> method;
    @FXML
    private TableColumn<History, History> select_Button;
    @FXML
    private TableColumn<History, History> preview_Button;
    @FXML
    private TableView<History> historyTable;
    private ObservableList<History> selectedHistoryFiles = FXCollections.observableArrayList();

    @FXML
    private RadioButton exportWithEncrypt_RadioButton;
    @FXML
    private RadioButton exportWithDecrypt_RadioButton;

    private ToggleGroup toggleGroup = new ToggleGroup();


    ObservableList<History> encryptHistoryList = FXCollections.observableArrayList();
    ObservableList<History> decryptHistoryList = FXCollections.observableArrayList();

    encrypt_history_data_DB historyData=new encrypt_history_data_DB();
    //saving_decrypt_path history_data_decrypt=new saving_decrypt_path();
    decrypt_history_data_DB decrypt_data=new decrypt_history_data_DB();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try{
            historyData.encrypt_data_came(encryptHistoryList);
//            System.out.println(encryptHistoryList);
        }catch (Exception e){
            System.out.println("Error to making Encrypt  List");
            System.out.println(e.toString());
        }
//Decrypt
        try{
            decrypt_data.Sender_data(decryptHistoryList);
//            System.out.println(decryptHistoryList);
        }catch (Exception e){
            System.out.println("Error to making Decrypt List");
            System.out.println(e.toString());
        }

//        new decrypt_history_data_DB().update_EncryptHistory(decryptHistoryList);



        sno.setCellValueFactory(cellData -> new SimpleIntegerProperty(historyTable.getItems().indexOf(cellData.getValue()) + 1).asObject());
        sno.setResizable(false);
        name.setCellValueFactory(new PropertyValueFactory<History, String>("name"));
        name.setResizable(false);
        path.setCellValueFactory(new PropertyValueFactory<History, String>("path"));       // The name in "" must be same as Data class attribute's  name
        path.setResizable(false);
        method.setCellValueFactory(new PropertyValueFactory<History, String>("method"));       // The name in "" must be same as Data class attribute's  name
        method.setResizable(false);


        select_Button.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        select_Button.setResizable(false);
        select_Button.setCellFactory(param -> new TableCell<History, History>() {
            private final Button selectButton = new Button("Select");

            @Override
            protected void updateItem(History file, boolean empty) {
                super.updateItem(file, empty);

                if (file == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(selectButton);
                selectButton.setStyle("-fx-cursor: hand");
                selectButton.setOnAction(event -> {
                    System.out.println(getItem().getPath());
//                    selectedHistoryFiles.add(getItem());

                    if (selectButton.getStyle().isEmpty()) {
                        selectButton.setStyle("-fx-background-color: #F46036; -fx-text-fill: white;");
                        selectedHistoryFiles.add(new History(getItem().getName(), getItem().getPath(), getItem().getMethod()));
                    } else {
                        selectButton.setStyle("");
                        selectedHistoryFiles.remove(getItem());
                    }
                });
            }

        });



        // Preview Button

        preview_Button.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        preview_Button.setResizable(false);
        preview_Button.setCellFactory(param -> new TableCell<History, History>() {
            private final Button previewButton = new Button("Preview");
            @Override
            protected void updateItem(History file, boolean empty) {
                super.updateItem(file, empty);

                if (file == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(previewButton);
                previewButton.setStyle("-fx-background-color: #F46036; -fx-text-fill: white; -fx-cursor: hand");
                previewButton.setOnAction(event -> {
                    System.out.println("Showing Preview of: " + getItem().getPath());
                });
            }

        });




        SortedList<History> sortedData = new SortedList<>(encryptHistoryList);
        sortedData.comparatorProperty().bind(historyTable.comparatorProperty());

        FilteredList<History> filteredData = new FilteredList<>(sortedData, p -> true);

        historyTable.setItems(filteredData);

        // Sort the table when clicking on the "Last Name" column header
        name.setComparator(String::compareToIgnoreCase);
        name.setSortType(TableColumn.SortType.ASCENDING);
        historyTable.getSortOrder().add(name);
        historyTable.sort();

        historyTable.refresh();


        // Add Radio Buttons in a Toggle Group
        exportWithEncrypt_RadioButton.setToggleGroup(toggleGroup);
        exportWithDecrypt_RadioButton.setToggleGroup(toggleGroup);

    }

    public void shareFiles(){
        System.out.println("Sharing Files");
    }

    public void exportWithDecrypt(){
        System.out.println("Export File After Decrypting");
    }

    public void exportWithEncrypt(){
        System.out.println("Export File After Encrypting");
    }

    public void selectAllFiles(){
         for(var file :  selectedHistoryFiles){
             System.out.println(file.getName() + " | " + file.getPath() + " | " + file.getMethod());
         }
    }

}
