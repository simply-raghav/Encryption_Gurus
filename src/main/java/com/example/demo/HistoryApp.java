package com.example.demo;

import animatefx.animation.BounceIn;
import javafx.application.Application;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryApp extends Application implements Initializable {
    public void start(Stage stage) throws Exception{
        try{
            Parent root = FXMLLoader.load(getClass().getResource("history.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("History!");
            new BounceIn(root).play();
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.setScene(scene);
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
    private TableColumn<Data, Integer> sno = new TableColumn<>("S.No.");;
    @FXML
    private TableColumn<Data, String> path;
    @FXML
    private TableColumn<Data, String> name;
    @FXML
    private TableView<Data> historyTable;

    ObservableList<Data> encryptHistoryList = FXCollections.observableArrayList();
    ObservableList<Data> decryptHistoryList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        encryptHistoryList.addAll(
                new Data("en_path1", "en_file1"),
                new Data("en_path2", "en_file2"),
                new Data("en_path3", "en_file3"),
                new Data("en_path4", "en_file4")
        );


        decryptHistoryList.addAll(
                new Data("de_path1", "de_file1"),
                new Data("de_path2", "de_file2"),
                new Data("de_path3", "de_file3"),
                new Data("de_path4", "de_file4")
        );

        sno.setCellValueFactory(cellData -> new SimpleIntegerProperty(historyTable.getItems().indexOf(cellData.getValue()) + 1).asObject());
        sno.setResizable(false);
        name.setCellValueFactory(new PropertyValueFactory<Data, String>("name"));
        name.setResizable(false);
        path.setCellValueFactory(new PropertyValueFactory<Data, String>("path"));       // The name in "" must be same as Data class attribute's  name
        path.setResizable(false);

        SortedList<Data> sortedData = new SortedList<>(encryptHistoryList);
        sortedData.comparatorProperty().bind(historyTable.comparatorProperty());

        FilteredList<Data> filteredData = new FilteredList<>(sortedData, p -> true);

        historyTable.setItems(filteredData);

        // Sort the table when clicking on the "Last Name" column header
        name.setComparator(String::compareToIgnoreCase);
        name.setSortType(TableColumn.SortType.ASCENDING);
        historyTable.getSortOrder().add(name);
        historyTable.sort();
    }
}
