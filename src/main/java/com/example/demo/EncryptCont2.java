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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class EncryptCont2 implements Initializable{

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

    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private Label selectedALgoTextArea, algoSelectedLabel, passwordLengthLabel;


    public void menuHandler(ActionEvent event) {
        try {
            List<String> selecetedMenu = new ArrayList<>();
            MenuItem selectedItem = (MenuItem) event.getSource();
            while (selectedItem.getParentMenu() != null) {
                selecetedMenu.add(selectedItem.getText());
                selectedItem = selectedItem.getParentMenu();
            }

            // How To Show Selected Algorithm from User

            String Algo = "";
            Collections.reverse(selecetedMenu);
            for (var str : selecetedMenu) {
                System.out.println(str);
                Algo += str;
                Algo += "/";
            }
            StringBuffer sb = new StringBuffer(Algo);
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
            Algo = sb.toString();

            if (selecetedMenu.size() > 0) {
//                AlgoPane.setVisible(true);
                selectedALgoTextArea.setText(Algo);
                algoSelectedLabel.setText("You have selected");
            }

            if (selectedALgoTextArea.getText().equals("AES/CBC-PKCS5Padding")) {
                System.out.println("AES/Padding");
                passwordLengthLabel.setText("* Password must be equal to 16 characters");
            }

            else if (selectedALgoTextArea.getText().equals("AES/ECB-NoPadding")) {
                System.out.println("AES/NoPadding");
                passwordLengthLabel.setText("* Password must be equal to 16 characters");

            }

            else if (selectedALgoTextArea.getText().equals("DES/CBC-PKCS5Padding")) {
                System.out.println("DES/CBC/Padding");
                passwordLengthLabel.setText("* Password must be equal to 8 characters");

            }

            else if (selectedALgoTextArea.getText().equals("DES/ECB-PKCS5Padding")) {
                System.out.println("DES/ECB/Padding");
                passwordLengthLabel.setText("* Password must be equal to 8 characters");

            }

            else if (selectedALgoTextArea.getText().equals("DESede/CBC-PKCS5Padding")) {
                System.out.println("Desede/Padding");
                passwordLengthLabel.setText("* Password must be equal to 24 characters");

            }


        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }
    public void homeScene(ActionEvent event){
        try{
            root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            new FadeIn(root).play();
            stage.centerOnScreen();
            stage.setResizable(false);
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
        stage.getIcons().add(new LogOutApp().icon);
        stage.setTitle("Secure Vault");
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

        System.out.println("HISTORY");
    }


    public void logoutScene(ActionEvent event){
        try{
            root = FXMLLoader.load(getClass().getResource("logout.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            this.stage.close();
            new LogOutApp().mainStage.setScene(scene);
            new FadeIn(root).play();
            new LogOutApp().mainStage.show();
        }catch(Exception e){
            System.out.println("Decrypt");
            System.out.println(e.toString());
        }

        System.out.println("LOGOUT");
    }

    public void decryptScene(ActionEvent event){
        try{
            root = FXMLLoader.load(getClass().getResource("decrypt.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            new FadeIn(root).play();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){
            System.out.println("Decrypt");
            System.out.println(e.toString());
        }

        System.out.println("LOGOUT");
    }


    //Password...
    @FXML
    PasswordField passwordField;
    @FXML
    private TextField textField;
    @FXML
    private ImageView hideButtonImage;


    // Function to show and hide password and toggle Image as well...
    public void togglePassword(ActionEvent event) throws Exception {

        if (passwordField.isManaged()) {
            passwordField.setManaged(false);
            passwordField.setVisible(false);
            textField.setText(passwordField.getText());

            // Change Eye Image
            hideButtonImage.setImage(new Image(getClass().getResourceAsStream("Images/eye2.png")));
        }else{
            passwordField.setText(textField.getText());
            passwordField.setVisible(true);
            passwordField.setManaged(true);

            // Change Eye Image
            hideButtonImage.setImage(new Image(getClass().getResourceAsStream("Images/eyeHide.png")));
        }
    }


    ObservableList<Data> list = FXCollections.observableArrayList();
    ObservableList<Data> selectedItems_toDecrypt = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        sno.setCellValueFactory(cellData -> new SimpleIntegerProperty(table.getItems().indexOf(cellData.getValue()) + 1).asObject());
        sno.setResizable(false);
        name.setCellValueFactory(new PropertyValueFactory<Data, String>("name"));
        name.setResizable(false);
        path.setCellValueFactory(new PropertyValueFactory<Data, String>("path"));       // The name in "" must be same as Data class attribute's  name
        path.setResizable(false);



        // Add Tooltip on Name Coloumn
        name.setCellFactory(
                column -> {
                    return new TableCell<Data, String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            setText(item);
                            setTooltip(new Tooltip(item));
                        }
                    };
                });

        // Add Tooltip on Path Coloumn
        path.setCellFactory(
                column -> {
                    return new TableCell<Data, String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            setText(item);
                            setTooltip(new Tooltip(item));
                        }
                    };
                });


        // Add Select Button into Table
        select_Button.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        select_Button.setResizable(false);
        select_Button.setCellFactory(param -> new TableCell<Data, Data>() {
            private final Button selectButton = new Button("Select");

            boolean temp = true;

            @Override
            protected void updateItem(Data entry, boolean empty) {
                selectButton.setStyle("-fx-cursor: hand");

                super.updateItem(entry, empty);

                if (entry == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(selectButton);
                // Function mentions that what work the Select button will does
                selectButton.setOnAction(event -> {

                    if (temp) {
                        temp = false;
                        selectedItems_toDecrypt.add(getItem());
                        selectButton.setStyle("-fx-background-color: #F46036; -fx-text-fill: white;");
                        System.out.println("File Seleceted");
                    } else {
                        temp = true;
                        selectButton.setStyle("-fx-cursor: hand;");
                        selectedItems_toDecrypt.remove(getItem());
                        System.out.println("File Not Selected");
                    }
                });
            };
        });



        // Add Delete Button into Table

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
                deleteButton.setStyle("-fx-cursor: hand");
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


    private static boolean isEncrypted = false;
    public void encryptFiles() throws SQLException, IOException, ClassNotFoundException {

        try{
            String key = passwordField.getText();
            for(var file : selectedItems_toDecrypt){

                // By Default Algo Method
                String AlgoMethod = selectedALgoTextArea.getText();


                System.out.println("File Name: " + file.getName());
                System.out.println("File Path: " + file.getPath());
                System.out.println("File Key: " + key);
                System.out.println("File Method: " + AlgoMethod);


                if (AlgoMethod.equalsIgnoreCase("AES/CBC-PKCS5Padding")) {
                    System.out.println("AES/Padding");
                    new AES_CBC_PKCS5Padding().encryptFile(file.getPath(), key);
                }

                else if (AlgoMethod.equalsIgnoreCase("AES/ECB-NoPadding")) {
                    System.out.println("AES/NoPadding");
                    new AES_ECB_NoPadding().encryptFile(file.getPath(), key);
                }

                else if (AlgoMethod.equalsIgnoreCase("DES/CBC-PKCS5Padding")) {
                    System.out.println("DES/CBC/Padding");
                    new DES_CBC_PKCS5Padding().encryptFile(file.getPath(), key);
                }

                else if (AlgoMethod.equalsIgnoreCase("DES/ECB-PKCS5Padding")) {
                    System.out.println("DES/ECB/Padding");
                    new DES_ECB_PKCS5Padding().encryptFile(file.getPath(), key);
                }

                else if (AlgoMethod.equalsIgnoreCase("Desede/CBC-PKCS5Padding")) {
                    System.out.println("Desede/Padding");
                    new Desede_CBC_PKCS5Padding().encryptFile(file.getPath(), key);
                }

                /* *******DataBase connectivity ************/
                new Saving_history_path().historySave(file.getName(), file.getPath(), AlgoMethod, key, "Encrypted");



                list.remove(file);
            }

//                new decrypt_history_data_DB().update_EncryptHistory(selectedItems_toDecrypt);


            /************************************/

            isEncrypted = true;

        }catch (Exception e){
            System.out.println(e.toString());
            System.out.println("safbashbdjvdfvbbdjhfb");
            JOptionPane.showMessageDialog(null, "Wrong Encryption Key", "ERROR", JOptionPane.ERROR_MESSAGE);
            isEncrypted = false;
        }
        finally {
            if(isEncrypted && !selectedItems_toDecrypt.isEmpty()){
                JOptionPane.showMessageDialog(null, "Files Encrypted Succesfully",
                        "INFORMATION",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }


        if(selectedItems_toDecrypt.isEmpty()){
            JOptionPane.showMessageDialog(null, "No file Selected",
                    "WARNING",
                    JOptionPane.WARNING_MESSAGE);
        }

        selectedItems_toDecrypt.clear();
//            for(var file : selectedItems_toDecrypt){
//                System.out.println("fsfnjk");
//            }
//            for(var file : list){
//                System.out.println(file.getName());
//            }

        initialize(null, null);
    }


    public void encryptFolders(){
        try {
            String key = passwordField.getText();
            for (var file : selectedItems_toDecrypt) {

                // By Default Algo Method
//                String AlgoMethod = selectedALgoTextArea.getText();
                System.out.println(file.getName());
                System.out.println(file.getPath());

                new FolderEncryptor().folderEnc(file.getPath(), key);

                JOptionPane.showMessageDialog(null, "Folder Encrypted", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);

            }
        }catch(Exception e){
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Folder Not Encrypted", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
    }



}