package org.example;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLOutput;
import java.util.*;
import java.io.*;
public class des_ecb_nopadding {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        try{
            BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
            String filepath=reader.readLine();
            String key=reader.readLine();
            System.out.println("Encrypt : 1\nDecrypt : 2");
            int a=sc.nextInt();
            switch(a){
                case 1: System.out.printencryption(filepath,key);
            }

            decryption(filepath,key);
        }catch(Exception e){
            e.printStackTrace();
        }




    }
    public static String encryption(String filepath,String key){
        try{
            SecretKeySpec secret=new SecretKeySpec(key.getBytes(),"DES");
            Cipher cipher= Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE,secret);
            Path real_file_path= Paths.get(filepath);
            byte filebyte[]= Files.readAllBytes(real_file_path);
            byte encryptbyte[]= cipher.doFinal(filebyte);
            Files.write(real_file_path,encryptbyte, StandardOpenOption.CREATE);
        }catch(Exception e){
            e.printStackTrace();
        }
        return  filepath;
    }
    public static String decryption(String filepath,String key){
        try{
            SecretKeySpec secret =new SecretKeySpec(key.getBytes(),"DES");
            Cipher cipher=Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE,secret);
            Path inputpath=Paths.get(filepath);
            byte encryptbyte[]=Files.readAllBytes(inputpath);
            byte decryptbyte[]= cipher.doFinal(encryptbyte);
            Files.write(inputpath,decryptbyte);

        }catch(Exception e){
            e.printStackTrace();
        }
        return filepath;
    }
}
