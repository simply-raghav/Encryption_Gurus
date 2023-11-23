package com.example.demo;

import javafx.collections.ObservableList;

import java.sql.*;

public class fetch_history_data {
    public void encrypt_data_came(ObservableList<History> List) throws ClassNotFoundException, SQLException {
        String filename="";
        String filepath="";
        String algorithm="";
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/history";
        String username="root";
        String password="0209rishi@Pa";
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
}