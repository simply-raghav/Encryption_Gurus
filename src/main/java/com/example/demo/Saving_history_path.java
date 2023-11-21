package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Saving_history_path {


        public void path_sending(String filepath,String type,String algorithm,String file_name,String key){
            try{
//                BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//                // give the all required details from the frontend path
//                System.out.println("Enter the Path");
//                String filepath=br.readLine();
//                System.out.println("Enter the Type");
//                String type=br.readLine();
//                System.out.println("Enter the algorithm");
//                String algorithm=br.readLine();

                String username="root";
                String password="0209rishi@Pa";
                //here you have to give your own system details
                String url="jdbc:mysql://localhost:3306/history";
                //loading class
                Class.forName("com.mysql.cj.jdbc.Driver");
                //making connection
                Connection con= DriverManager.getConnection(url,username,password);

                //query  filepath , type(Encrypted or decrypted) , Algo name
                long temp_key=Long.parseLong(key);
                String q="Insert INTO history_management(Path,Type,Algorithm,filename,keyvalue) VALUES(?,?,?,?,?)";
                PreparedStatement pstmt=con.prepareStatement(q);
                pstmt.setString(1,filepath);
                pstmt.setString(2,type);
                pstmt.setString(3,algorithm);
                pstmt.setString(4,file_name);
                pstmt.setLong(5,temp_key);
                pstmt.executeUpdate();
                System.out.println("Updated successfully...");
            }catch(Exception e){
                e.printStackTrace();
            }
        }


    }

