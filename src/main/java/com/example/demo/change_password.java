package com.example.demo;

import javax.swing.*;
import java.sql.*;

public class change_password {
    public void changePassword(String newpwd) throws ClassNotFoundException {

        String the_Usrname = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/login";
            String username="root";
            String password="0209rishi@Pa";
//            String password="admin";
            Connection con= DriverManager.getConnection(url,username,password);
            String q="update login_info set password= ? where username = ?";
            PreparedStatement pstmt=con.prepareStatement(q);

            the_Usrname = getUserName();

            pstmt.setString(1,newpwd);
            pstmt.setString(2,the_Usrname);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Password has been updated Successfully...");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Not changed password...");
        }
    }



    public String getUserName() throws ClassNotFoundException {
        String Uname="";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/login";
            String username="root";
            String password="0209rishi@Pa";
//            String password="admin";
            Connection con= DriverManager.getConnection(url,username,password);
            String q="select  username from login_info";
           Statement stmt=con.createStatement();
            ResultSet resultSet=stmt.executeQuery(q);
            while (resultSet.next()){
                 Uname=resultSet.getString("username");
                System.out.println(Uname);
            }
           // JOptionPane.showMessageDialog(null,"Password has been updated Successfully...");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Not changed password...");
        }
        return Uname;
    }
}
