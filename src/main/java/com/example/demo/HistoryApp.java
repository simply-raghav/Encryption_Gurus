package com.example.demo;

import animatefx.animation.FadeIn;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
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
            e.printStackTrace();
        }
    }

    @FXML
    private Button encryptButton, decryptButton;
    boolean encryptBoolean = true;
    public void showEncryptedHistory(ActionEvent event){
        encryptBoolean = true;
        encryptButton.setStyle("-fx-background-color: F46036; -fx-text-fill: FFF; -fx-background-radius: 10");
        decryptButton.setStyle("-fx-background-color: FFF; -fx-text-fill: F46036; -fx-background-radius: 10");
        System.out.println("Encrypt");

        //set All elements from Encrypt files History to history-Table
        historyTable.setItems(encryptHistoryList);
    }
    public void showDecryptedHistory(ActionEvent event){
        encryptBoolean = false;
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
    private TableColumn<History, Integer> sno = new TableColumn<>("S.No.");
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
                    if(encryptBoolean){
                        // Previewing the files without decrypting them
                        try {
                            previewEncryptedFile(file);
                        } catch (SQLException | IOException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Encrypted Files");
                    }else{
                        previewDecryptedFile(file);
                        System.out.println("Decrypted Files");
                    }
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

    public byte[] decryptFiles(String key, History file) throws SQLException, IOException, ClassNotFoundException {

        /**************************Connectivity to DB***************************/
        searching_algo_DB search=new searching_algo_DB();
        boolean isDecrypted = false;
        Path inputPath = Paths.get(file.getPath());
        byte[] encryptedBytes = null;
        byte[] decryptedBytes = null;

        try {
            encryptedBytes = Files.readAllBytes(inputPath);
        }catch (IOException e) {
            System.out.println(e);
        }
        try{
            String AlgoMethod= search.algo_finder(file.getPath());            // To fetch Algo Method from encrypt_history DB

            if (AlgoMethod.equalsIgnoreCase("AES/CBC/PKCS5Padding")) {
                System.out.println("AES/Padding");
                decryptedBytes = new AES_CBC_PKCS5Padding().decryptFile(encryptedBytes, key);
            }

            else if (AlgoMethod.equalsIgnoreCase("AES/ECB/NoPadding")) {
                System.out.println("AES/NoPadding");
                decryptedBytes = new AES_ECB_NoPadding().decryptFile(encryptedBytes, key);
            }

            else if (AlgoMethod.equalsIgnoreCase("DES/CBC/PKCS5Padding")) {
                System.out.println("DES/CBC/Padding");
                decryptedBytes = new DES_CBC_PKCS5Padding().decryptFile(encryptedBytes, key);
            }

            else if (AlgoMethod.equalsIgnoreCase("DES/ECB/PKCS5Padding")) {     // Isme Dikkt hai
                System.out.println("DES/ECB/Padding");
                decryptedBytes = new DES_ECB_PKCS5Padding().decryptFile(encryptedBytes, key);
            }

            else if (AlgoMethod.equalsIgnoreCase("Desede/CBC/PKCS5Padding")) {
                System.out.println("Desede/Padding");
                decryptedBytes = new Desede_CBC_PKCS5Padding().decryptFile(encryptedBytes, key);
            }

            isDecrypted = true;
            /********************************************************************************************************/

        }catch (Exception e){
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Wrong Encryption Key", "ERROR", JOptionPane.ERROR_MESSAGE);
            isDecrypted = false;
        }
        finally {
            if(isDecrypted){
                JOptionPane.showMessageDialog(null, "Files Decrypted Succesfully",
                        "INFORMATION",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return decryptedBytes;
    }

    private void previewEncryptedFile(History file) throws SQLException, IOException, ClassNotFoundException {
        String key = showDialogBox();

        if (key!= null) {
            byte[] decryptedBytes = decryptFiles(key, file);
            if(file.getPath().endsWith(".mp4")) {
                viewVideoFileEncrypted(decryptedBytes);
            }
            else if(file.getPath().endsWith(".txt")) {
                viewTextFileEncrypted(decryptedBytes, key, file.getPath());
            }
            else if(file.getPath().endsWith(".jpg")) {
                viewImageFileEncrypted_jpg(decryptedBytes);
            }else{
                JOptionPane.showMessageDialog(null, "File Not Supported to view.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                previewEncryptedFile(file);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Enter Key Please",
                    "INFORMATION",
                    JOptionPane.INFORMATION_MESSAGE);
            previewEncryptedFile(file);
        }
    }

    private void viewImageFileEncrypted_jpg(byte[] decryptedBytes) {
        File file = new File("D:/img.jpg");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Files.write(Path.of(file.getAbsolutePath()), decryptedBytes);
        }catch (IOException e) {
            e.printStackTrace();
        }
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView(image);
        Group root = new Group();
        root.getChildren().add(imageView);
        Scene scene = new Scene(root, image.getWidth(), image.getHeight());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }


    private void viewTextFileEncrypted(byte[] decryptedBytes, String key, String originalPath) {
        File file = new File("D:/temp.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Files.write(Path.of(file.getAbsolutePath()), decryptedBytes);
        }catch (IOException e) {
            e.printStackTrace();
        }
        textArea = new TextArea();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            textArea.setText(content.toString());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("File");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As");

        menu.getItems().addAll(saveItem, saveAsItem);
        saveItem.setOnAction(e -> saveFileEncrypted(originalPath, key));
        saveAsItem.setOnAction(e -> saveFileAsDecrypted());

        menuBar.getMenus().add(menu);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(textArea);
        Scene scene = new Scene(borderPane, 800, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void saveFileEncrypted(String path, String key) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(textArea.getText());
            writer.close();
            searching_algo_DB search=new searching_algo_DB();
            String AlgoMethod= search.algo_finder(path);

            if (AlgoMethod.equalsIgnoreCase("AES/CBC/PKCS5Padding")) {
                System.out.println("AES/Padding");
                new AES_CBC_PKCS5Padding().encryptFile(path, key);
            }

            else if (AlgoMethod.equalsIgnoreCase("AES/ECB/NoPadding")) {
                System.out.println("AES/NoPadding");
                new AES_ECB_NoPadding().encryptFile(path, key);
            }

            else if (AlgoMethod.equalsIgnoreCase("DES/CBC/PKCS5Padding")) {
                System.out.println("DES/CBC/Padding");
                new DES_CBC_PKCS5Padding().encryptFile(path, key);
            }

            else if (AlgoMethod.equalsIgnoreCase("DES/ECB/PKCS5Padding")) {
                System.out.println("DES/ECB/Padding");
                new DES_ECB_PKCS5Padding().encryptFile(path, key);
            }

            else if (AlgoMethod.equalsIgnoreCase("Desede/CBC/PKCS5Padding")) {
                System.out.println("Desede/Padding");
                new Desede_CBC_PKCS5Padding().encryptFile(path, key);
            }





        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void viewVideoFileEncrypted(byte[] decryptedBytes) {
        File file = new File("D:/video.mp4");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Files.write(Path.of(file.getAbsolutePath()), decryptedBytes);
        }catch (IOException e) {
            e.printStackTrace();
        }
        Media media = new Media(file.toURI().toString());
        MediaPlayer mp = new MediaPlayer(media);
        mp.setAutoPlay(true);
        MediaView mediaView = new MediaView(mp);
        Group root = new Group();
        root.getChildren().add(mediaView);
        Scene scene = new Scene(root, mp.getMedia().getWidth(), mp.getMedia().getHeight());
        Stage stage = new Stage();
        mediaView.setFitHeight(stage.getHeight());
        mediaView.setFitWidth(stage.getWidth());
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                mp.stop();
                mp.dispose();
                file.delete();
            }
        });
    }

    private String showDialogBox() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Password Dialog Box");
        dialog.setHeaderText("Enter Your Password:");

        // Set the button types.
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Create the content grid.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // Create and add controls to the grid.
        PasswordField passwordField = new PasswordField();
        grid.add(new Label("Input:"), 0, 0);
        grid.add(passwordField, 1, 0);

        // Set the content of the dialog.
        dialog.getDialogPane().setContent(grid);

        // Request focus on the input field by default.
        Platform.runLater(() -> passwordField.requestFocus());

        // Convert the result to a string when the OK button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return passwordField.getText();
            }
            return null;
        });

        // Show the dialog and wait for the user's response.
        String result = dialog.showAndWait().orElse(null);

        if (result != null) {
            System.out.println("User entered: " + result);
        }
        return result;
    }

    private void previewDecryptedFile(History file) {
        if(file.getPath().endsWith(".mp4")) {
            viewVideoFileDecrypted(file.getPath());
        }
        if(file.getPath().endsWith(".txt")) {
            viewTextFileDecrypted(file.getPath());
        }
        if(file.getPath().endsWith(".jpg") || file.getPath().endsWith(".jpeg") || file.getPath().endsWith(".gif") || file.getPath().endsWith(".png") || file.getPath().endsWith(".svg")) {
            viewImageFileDecrypted(file.getPath());
        }

    }

    private void viewImageFileDecrypted(String path) {
        Image image = new Image(path);
        ImageView imageView = new ImageView(image);
        Group root = new Group();
        root.getChildren().add(imageView);
        Scene scene = new Scene(root, image.getWidth(), image.getHeight());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    TextArea textArea;
    private void viewTextFileDecrypted(String path) {
        textArea = new TextArea();
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            textArea.setText(content.toString());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("File");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As");

        menu.getItems().addAll(saveItem, saveAsItem);
        saveItem.setOnAction(e -> saveFileDecrypted(path));
        saveAsItem.setOnAction(e -> saveFileAsDecrypted());

        menuBar.getMenus().add(menu);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(textArea);
        Scene scene = new Scene(borderPane, 800, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void saveFileAsDecrypted() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File As");
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(textArea.getText());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFileDecrypted(String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(textArea.getText());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewVideoFileDecrypted(String path) {
        Media media = new Media(path);
        MediaPlayer mp = new MediaPlayer(media);
        mp.setAutoPlay(true);

        MediaView mediaView = new MediaView(mp);
        Group root = new Group();
        root.getChildren().add(mediaView);
        Scene scene = new Scene(root, mp.getMedia().getWidth(), mp.getMedia().getHeight());
        Stage stage = new Stage();
        mediaView.setFitHeight(stage.getHeight());
        mediaView.setFitWidth(stage.getWidth());
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                mp.stop();
                mp.dispose();
            }
        });
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
