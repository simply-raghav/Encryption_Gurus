



package com.example.demo;

        import animatefx.animation.BounceIn;
        import animatefx.animation.FadeIn;
        import javafx.application.Application;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Label;
        import javafx.scene.control.PasswordField;
        import javafx.scene.control.TextField;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.stage.Stage;

        import java.io.IOException;

public class UserRegisterApp extends Application {

    private Parent root;
    private Stage stage;
    private Scene scene;
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        root = FXMLLoader.load(getClass().getResource("userRegister.fxml"));
        scene = new Scene(root);
        new FadeIn(root).play();

        stage.setTitle("Secure Vault");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("Images/logo.png")));
        stage.setResizable(false);
        stage.centerOnScreen();

        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private PasswordField newPasswordField;
    @FXML
    private TextField textField, usernameField, emailField;

    @FXML
    private ImageView hideButtonImage;
    public void togglePassword(ActionEvent event) throws Exception {
        if (newPasswordField.isManaged()) {
            newPasswordField.setManaged(false);
            newPasswordField.setVisible(false);
            String password = newPasswordField.getText();
            textField.setText(password);
            hideButtonImage.setImage(new Image(getClass().getResourceAsStream("Images/eye2.png")));
        } else {
            newPasswordField.setVisible(true);
            newPasswordField.setManaged(true);
            textField.setText("");
            hideButtonImage.setImage(new Image(getClass().getResourceAsStream("Images/eyeHide.png")));
        }
    }

    public void back(ActionEvent event){
        try{
            System.out.println("Back");

            root = FXMLLoader.load(getClass().getResource("logout.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            new BounceIn(root).play();
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public boolean isValid(String mail){
        String regex = "^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return mail.matches(regex);
    }


    @FXML
    private Label passwordValidateLabel, EmailValidateLabel;

    public void registerUser(ActionEvent event){

        System.out.println("User Register Successfully");
        System.out.println("UserName : " + usernameField.getText());
        System.out.println("Email : " + emailField.getText());
        System.out.println("Password : " + newPasswordField.getText());




        boolean passFlag = false, emailFlag = false;


        if(newPasswordField.getText().length() <= 8){
            passwordValidateLabel.setVisible(true);     //  Show PAssowrd Validata Lable
            passFlag = false;
            newPasswordField.setText("");
        }else{
            passwordValidateLabel.setVisible(false);    // Hide PAssowrd Validata Lable
            passFlag = true;
        }

        if(isValid(emailField.getText())){
            emailFlag = true;
        }else{
            emailFlag = false;
        }

        try{

            if(passFlag && emailFlag){

                Check_login_DB.enter_login_data(usernameField.getText(), newPasswordField.getText(), emailField.getText());

                root = FXMLLoader.load(getClass().getResource("logout.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                new BounceIn(root).play();
                stage.centerOnScreen();
                stage.setResizable(false);
                stage.show();
            }

        }catch (Exception e){
            System.out.println(e.toString());
        }
    }


}