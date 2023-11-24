package com.example.demo;

import java.io.IOException;
import java.sql.*;

public class searching_algo_DB {
    public String algo_finder(String path) throws ClassNotFoundException, SQLException, IOException {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
////                // give the all required details from the frontend path
////
//                System.out.println("Enter the Path");
//                String filepath=br.readLine();
        String algo="";
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost/history";
        String username="root";
        String password="0209rishi@Pa";
        Connection con= DriverManager.getConnection(url,username,password);
        String q="select algorithm from encrypt_history where filepath =?";
        PreparedStatement pstmt=con.prepareStatement(q);
        pstmt.setString(1,path);
        ResultSet resultSet=pstmt.executeQuery();
        while(resultSet.next()){
            algo= resultSet.getString("algorithm");
            System.out.println("Algorithm : "+algo);
        }
        return algo;
    }
}
