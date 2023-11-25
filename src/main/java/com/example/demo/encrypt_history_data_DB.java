package com.example.demo;

import javafx.collections.ObservableList;

import java.sql.*;

public class encrypt_history_data_DB {
    public void encrypt_data_came(ObservableList<History> List) throws ClassNotFoundException, SQLException {
        String filename="";
        String filepath="";
        String algorithm="";
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/history";
        String username="root";
        String password="0209rishi@Pa";
//        String password= "admin";

        Connection con=DriverManager.getConnection(url,username,password);
        String q="Select filename,filepath,algorithm from encrypt_history";
        Statement stmt=con.createStatement();
        ResultSet resultSet= stmt.executeQuery(q);

        while (resultSet.next()){
            filename=resultSet.getString("filename");
            filepath=resultSet.getString("filepath");
            algorithm=resultSet.getString("algorithm");
           //
            List.addAll(
                    new History(filename, filepath, algorithm)
            );
            System.out.println("FileName : "+filename);
            System.out.println("FilePath : "+filepath);
            System.out.println("Algorithm :"+algorithm);
            System.out.println("-----------------------");
        }
    }
    public void delete_encrypt_data(String filepath) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/history";
            String username = "root";
            String password = "0209rishi@Pa";
//            String password= "admin";

            try (Connection con = DriverManager.getConnection(url, username, password)) {
                String q = "DELETE FROM encrypt_history WHERE filepath = ?";
                try (PreparedStatement pstmt = con.prepareStatement(q)) {
                    pstmt.setString(1, filepath);
                    pstmt.executeUpdate();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error in encrypt_history_data_DB.delete_encrypt_data()");
            System.out.println(e.toString());
//            e.printStackTrace();
            // Handle exceptions more gracefully in a production environment
        }
    }

}
