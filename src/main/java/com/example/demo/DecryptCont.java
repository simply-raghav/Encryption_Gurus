package com.example.demo;

import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DecryptCont implements Initializable{

        @FXML
        private TableColumn<Data, Data> delete_Button;
        @FXML
        private TableColumn<Data, Data> select_Button;
        @FXML
        private TableColumn<Data, Integer> sno;
        @FXML
        private TableColumn<Data, String> path;
        @FXML
        private TableColumn<Data, String> name;
        @FXML
        private TableView<Data> table;

        // Common Variables...
        private List<File> files;
        private List<String> selectedFiles = new ArrayList<>();
        private int cnt = 1;

        //Password...
        @FXML
        PasswordField passwordField = new PasswordField();
        @FXML
        private TextField textField = new TextField();

private Stage stage;
private Parent root;
private Scene scene;

    public void homeScene(ActionEvent event){
        try{
            root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            new FadeIn(root).play();
            stage.show();
        }catch (Exception e){
            System.out.println("Error in homeScene");
            System.out.println(e.toString());
        }
    }

    public void historyScene(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("history.fxml"));
        root = (Parent) loader.load();
        stage = new Stage();
        new FadeIn(root).play();
        stage.setScene(new Scene(root));
        stage.show();

        System.out.println("HISTORY");
    }


    public void logoutScene(ActionEvent event){
        try{
            root = FXMLLoader.load(getClass().getResource("logout.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            new FadeIn(root).play();
            stage.show();
        }catch(Exception e){
            System.out.println("Decrypt");
            System.out.println(e.toString());
        }

        System.out.println("LOGOUT");
    }

    public void encryptScene(ActionEvent event){
        try{
            root = FXMLLoader.load(getClass().getResource("encrypt.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            new FadeIn(root).play();
            stage.show();
        }catch(Exception e){
            System.out.println("Decrypt");
            System.out.println(e.toString());
        }

        System.out.println("LOGOUT");
    }


        @FXML
        public void togglePassword(ActionEvent event) throws Exception {
            if (passwordField.isManaged()) {
                passwordField.setManaged(false);
                passwordField.setVisible(false);
                String password = passwordField.getText();
                textField.setText(password);
//            imageView.setImage(eyeImage);
            } else {
                passwordField.setVisible(true);
                passwordField.setManaged(true);
                textField.setText("");
//            imageView.setImage(eye_SlashImage);
            }
        }


        ObservableList<Data> list = FXCollections.observableArrayList();

        public void initialize(URL url, ResourceBundle resourceBundle) {
            sno.setCellValueFactory(cellData -> new SimpleIntegerProperty(table.getItems().indexOf(cellData.getValue()) + 1).asObject());
            sno.setResizable(false);
            name.setCellValueFactory(new PropertyValueFactory<Data, String>("name"));
            name.setResizable(false);
            path.setCellValueFactory(new PropertyValueFactory<Data, String>("path"));       // The name in "" must be same as Data class attribute's  name
            path.setResizable(false);


            select_Button.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
            select_Button.setResizable(false);
            select_Button.setCellFactory(param -> new TableCell<Data, Data>() {
                private final Button selectButton = new Button("Select");

                @Override
                protected void updateItem(Data person, boolean empty) {
                    super.updateItem(person, empty);

                    if (person == null) {
                        setGraphic(null);
                        return;
                    }

                    setGraphic(selectButton);
                    selectButton.setOnAction(event -> {
                        System.out.println(getItem().getPath());
                        selectedFiles.add(getItem().getPath());

                        if (selectButton.getStyle().isEmpty()) {
                            selectButton.setStyle("-fx-background-color: #F46036; -fx-text-fill: white;");
                        } else {
                            selectButton.setStyle("");
                        }
                    });
                };
            });




            delete_Button.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
            delete_Button.setCellFactory(param -> new TableCell<Data, Data>() {
                private final Button deleteButton = new Button("Delete");

                @Override
                protected void updateItem(Data person, boolean empty) {
                    super.updateItem(person, empty);

                    if (person == null) {
                        setGraphic(null);
                        return;
                    }

                    setGraphic(deleteButton);
                    deleteButton.setOnAction(event -> list.remove(person));
                }

            });

            SortedList<Data> sortedData = new SortedList<>(list);
            sortedData.comparatorProperty().bind(table.comparatorProperty());

            FilteredList<Data> filteredData = new FilteredList<>(sortedData, p -> true);

            table.setItems(filteredData);

            // Sort the table when clicking on the "Last Name" column header
            name.setComparator(String::compareToIgnoreCase);
            name.setSortType(TableColumn.SortType.ASCENDING);
            table.getSortOrder().add(name);
            table.sort();

        }

        public void addElement(ActionEvent event) {
            files = new FileChooser().showOpenMultipleDialog(null);
            if (files != null) {
                for (var oneFile : files) {
                    String PATH = oneFile.getAbsolutePath();
                    String NAME = oneFile.getName();
                    list.add(new Data(PATH, NAME));
//                System.out.println(oneFile.getAbsolutePath());
                }
            } else {
                System.out.println("No File Taken");
            }
            event.consume();
        }

        @FXML
        public void handleDragOver(DragEvent event) {
            if (event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
            event.consume();
        }

        @FXML
        public void handleDragDropped(DragEvent event) {
            files = event.getDragboard().getFiles();
            for (var myfile : files) {
                String PATH = myfile.getAbsolutePath();
                String NAME = myfile.getName();
                list.add(new Data(PATH, NAME));
                System.out.println("Dropped file: " + myfile.getAbsolutePath());
            }

            event.setDropCompleted(true);
            event.consume();
        }


        public void encryptFiles(){
            String key = passwordField.getText();
        for(var file : selectedFiles){
            EncryptAlgo obj = new EncryptAlgo(file);
            obj.print();
//            System.out.println(file.getAbsolutePath() + "-> " + key);
//            obj.encryptFile(file.getAbsolutePath(), key);
            obj.decryptFile(file, key);

        }


            selectedFiles.clear();
            select_Button.setStyle("");

//            for(String tempPath : selectedFiles){
//                System.out.println(tempPath + ": " + key);
//            }

        }

}
