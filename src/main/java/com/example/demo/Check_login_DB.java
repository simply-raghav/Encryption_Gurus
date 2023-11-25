package com.example.demo;

import java.sql.*;

public class Check_login_DB {


    public static boolean isExit() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/login";
        String username="root";
        String password="0209rishi@Pa";
        Connection con = DriverManager.getConnection(url,username,password);
        Statement stmt = con.createStatement();
        String q = "select *from login_info";
        ResultSet resultSet = stmt.executeQuery(q);
        while(resultSet.next()){
            String data=resultSet.getString("username");
            System.out.println(data);
            return true;
        }
        return false;
    }

    public static void enter_login_data(String username,String password,String emailId) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/login";
        String user="root";
        String pwd="0209rishi@Pa";
        Connection con = DriverManager.getConnection(url,user,pwd);
        //Statement stmt = con.createStatement();
        String q = "INSERT into login_info(username,password,emailId) values(?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(q);
        pstmt.setString(1,username);
        pstmt.setString(2,password);
        pstmt.setString(3,emailId);
        pstmt.executeUpdate();
        System.out.println("Updated successfully");
    }


}
