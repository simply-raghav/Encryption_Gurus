package com.example.prac1.Data_to_sql_history;

import java.sql.*;

public class Fetching_data {
    public static void main(String[] args) {
        try{
            //class loader
            Class.forName("com.mysql.cj.jdbc.Driver");
            // system details
            String username="root";
            String password="0209rishi@Pa";
            String url="jdbc:mysql://localhost:3306/history";

            // making connection to the database

            Connection con= DriverManager.getConnection(url,username,password);

            // Query

            String q="Select * from history_management";

           // Statement stmt =con.createStatement();
            PreparedStatement pstmt=con.prepareStatement(q);
            ResultSet result=pstmt.executeQuery();

            while(result.next()){

                // Here you will get all Data (From here you can take and show on UI)

                String path=result.getString("Path");
                String type= result.getString("Type");
                String algo=result.getString("Algorithm");

                //currently showing on the console
                System.out.println();
                System.out.println("Path : "+path+"  Type: "+type+"  Algorithm : "+algo);

                System.out.println();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
