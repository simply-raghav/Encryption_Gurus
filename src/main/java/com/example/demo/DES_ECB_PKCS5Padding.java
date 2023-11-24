package com.example.demo;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;



import java.io.*;
import java.util.Scanner;

public class DES_ECB_PKCS5Padding {

        // **** DES key msut be equals to (8 characters)... ****

        public static String encryptFile(String filePath, String key) throws Exception {
                System.out.println("Encyption: " + filePath + " and " + key);

                SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "DES");
                Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);

                Path inputPath = Paths.get(filePath);
                byte[] fileBytes = Files.readAllBytes(inputPath);
                byte[] encryptedBytes = cipher.doFinal(fileBytes);

                String encryptedcontent= Base64.getEncoder().encodeToString(encryptedBytes);
                Files.write(Paths.get(filePath),encryptedBytes, StandardOpenOption.CREATE);
                // String encryptedFilePath = filePath + ".encrypted";
                // Path outputPath = Paths.get(encryptedFilePath);
                //Files.write(outputPath, encryptedBytes);

                return filePath;
        }

        //decryption work done here
        public static String decryptFile(String filePath, String key) throws Exception{

                SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "DES");
                Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, secretKey);

                Path inputPath = Paths.get(filePath);
                byte[] encryptedBytes = Files.readAllBytes(inputPath);
                byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

                // String decryptedFilePath = filePath.replace(".encrypted", ".decrypted");
                String decryptedcontent=Base64.getEncoder().encodeToString(decryptedBytes);
                // Path outputPath = Paths.get(decryptedFilePath);
                Files.write(Paths.get(filePath), decryptedBytes);

                return filePath;
        }
}




