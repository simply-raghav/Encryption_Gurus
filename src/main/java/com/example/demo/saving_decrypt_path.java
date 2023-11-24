package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class saving_decrypt_path {
    public void send_decrypt_data(String filename,String filepath,String algorithm,String keyvalue,String type) throws ClassNotFoundException, SQLException, IOException {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//                // give the all required details from the frontend path
//
//                System.out.println("Enter the Path");
//                String filepath=br.readLine();
//                System.out.println("Enter the Type");
//                String type=br.readLine();
//                System.out.println("Enter the algorithm");
//                String algorithm=br.readLine();
//        System.out.println("File Name : ");
//        String filename= br.readLine();
//        System.out.println("Key : ");
//        String keyvalue= br.readLine();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/history";
        String username="root";
//        String password="0209rishi@Pa";
        String password= "admin";

        Connection con= DriverManager.getConnection(url,username,password);
        String q="Insert INTO decrypt_history(filename,filepath,algorithm,keyvalue,type) values(?,?,?,?,?)";
        PreparedStatement pstm=con.prepareStatement(q);
        pstm.setString(1,filename);
        pstm.setString(2,filepath);
        pstm.setString(3,algorithm);
        pstm.setString(4,keyvalue);
        pstm.setString(5,type);
        pstm.executeUpdate();
        System.out.println("Updated successfully...");
    }
}
