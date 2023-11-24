package com.example.demo;

import javafx.collections.ObservableList;

import java.sql.*;

public class decrypt_history_data_DB {
    public void Sender_data(ObservableList<History> List) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/history";
        String username="root";
        String password="0209rishi@Pa";
//        String password="admin";
        Connection con= DriverManager.getConnection(url,username,password);
        String q="select *from decrypt_history";
        Statement stmt=con.createStatement();
        ResultSet resultSet=stmt.executeQuery(q);
        while(resultSet.next()){
            String filename=resultSet.getString("filename");
            String filepath=resultSet.getString("filepath");
            String algo=resultSet.getString("algorithm");
            System.out.println("joh chahiye woh wala sout");
            System.out.println(filename);
            System.out.println(filepath);
            System.out.println(algo);
            List.addAll(
                    new History(filename, filepath, algo)
            );
        }
    }
//    public void delete_encrypt_data(String filepath) {
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            String url = "jdbc:mysql://localhost:3306/history";
//            String username = "root";
//            String password = "0209rishi@Pa";
//
//            try (Connection con = DriverManager.getConnection(url, username, password)) {
//                String q = "DELETE FROM encrypt_history WHERE filepath = ?";
//                try (PreparedStatement pstmt = con.prepareStatement(q)) {
//                    pstmt.setString(1, filepath);
//                    pstmt.executeUpdate();
//                }
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            // Handle exceptions more gracefully in a production environment
//        }
//    }




    public void update_EncryptHistory(ObservableList<Data> List){
        for(var file : List){

            System.out.println("DECryp : ----> " + file.getPath());



            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/history";
                String username = "root";
                String password = "0209rishi@Pa";
//                String password= "admin";
                try (Connection con = DriverManager.getConnection(url, username, password)) {
                    String q = "DELETE FROM encrypt_history WHERE filepath = ?";
                    try (PreparedStatement pstmt = con.prepareStatement(q)) {
                        pstmt.setString(1, file.getPath());
                        pstmt.executeUpdate();
                    }
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                // Handle exceptions more gracefully in a production environment
            }

//            obj.encryptHistoryList.clear();
//            obj.decryptHistoryList.clear();
//            new HistoryApp().initialize(null,null);

        }
    }
    public void update_DecryptHistory(ObservableList<Data> List){
        for(var file : List){

            System.out.println("DECryp : ----> " + file.getPath());



            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/history";
                String username = "root";
                String password = "0209rishi@Pa";
//                String password= "admin";

                try (Connection con = DriverManager.getConnection(url, username, password)) {
                    String q = "DELETE FROM decrypt_history WHERE filepath = ?";
                    try (PreparedStatement pstmt = con.prepareStatement(q)) {
                        pstmt.setString(1, file.getPath());
                        pstmt.executeUpdate();
                    }
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                // Handle exceptions more gracefully in a production environment
            }

//            obj.encryptHistoryList.clear();
//            obj.decryptHistoryList.clear();
//            new HistoryApp().initialize(null,null);

        }
    }
}
