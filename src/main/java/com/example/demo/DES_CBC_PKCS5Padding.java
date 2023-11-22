package com.example.demo;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.Scanner;

public class DES_CBC_PKCS5Padding {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the file path: ");
            String filePath = scanner.nextLine();

            System.out.print("Enter the DES key (8 characters): ");
            String key = scanner.nextLine();

            // Encrypt the file
            String encryptedFilePath = encryptFileDES_CBC_PKCS5Padding(filePath, key);
            System.out.println("File encrypted successfully. Encrypted file: " + encryptedFilePath);

            // Decrypt the file
            String decryptedFilePath = decryptFileDES_CBC_PKCS5Padding(encryptedFilePath, key);
            System.out.println("File decrypted successfully. Decrypted file: " + decryptedFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encryptFileDES_CBC_PKCS5Padding(String filePath, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        // Convert the key to a SecretKey
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "DES");

        // Generate a random Initialization Vector (IV)
        IvParameterSpec iv = new IvParameterSpec(new byte[8]);

        // Initialize the cipher for encryption
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
        byte[] encryptedBytes = cipher.doFinal(fileBytes);

        // Encode the encrypted content using Base64
        String encryptedContent = Base64.getEncoder().encodeToString(encryptedBytes);

        Path encryptedFilePath = Paths.get(filePath + ".encrypted");
        Files.write(encryptedFilePath, encryptedContent.getBytes(), StandardOpenOption.CREATE);

        return encryptedFilePath.toString();
    }

    public static String decryptFileDES_CBC_PKCS5Padding(String filePath, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        // Convert the key to a SecretKey
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "DES");

        // Use the first 8 bytes of the file as the IV
        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
        IvParameterSpec iv = new IvParameterSpec(fileBytes, 0, 8);

        // Initialize the cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

        // Decrypt the content
        byte[] encryptedBytes = cipher.doFinal(fileBytes, 8, fileBytes.length - 8);

        Path decryptedFilePath = Paths.get(filePath + ".decrypted");
        Files.write(decryptedFilePath, encryptedBytes, StandardOpenOption.CREATE);

        return decryptedFilePath.toString();
    }
}

