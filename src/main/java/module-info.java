module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires AnimateFX;
    requires java.desktop;
    requires java.sql;
    requires java.mail;
    requires mysql.connector.j;
    requires activation;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
//    exports com.example.demo.Controllers;
//    opens com.example.demo.Controllers to javafx.fxml;
}