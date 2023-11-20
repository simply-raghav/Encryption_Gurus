package com.example.demo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class change_password {
    public void changePassword(String newpwd) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/login";
            String username="root";
            String password="0209rishi@Pa";
            Connection con= DriverManager.getConnection(url,username,password);
            String q="update login_info set password= ? where username = ?";
            PreparedStatement pstmt=con.prepareStatement(q);
            pstmt.setString(1,newpwd);
            pstmt.setString(2,"encrypt123");

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Password has been updated Successfully...");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Not changed password...");
        }
    }
}
