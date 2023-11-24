package com.example.demo;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.Scanner;

public class AES_ECB_NoPadding {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the file path: ");
            String filePath = scanner.nextLine();

            System.out.print("Enter the AES key (16 characters): ");
            String key = scanner.nextLine();

            // Encrypt the file
            //String encryptedFilePath = encryptFile(filePath, key);
            //System.out.println("File encrypted successfully. Encrypted file: " + encryptedFilePath);

            // Decrypt the file
            String decryptedFilePath = decryptFile(filePath, key);
            System.out.println("File decrypted successfully. Decrypted file: " + decryptedFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encryptFile(String filePath, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");

        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));

        // Pad the input manually to match block size
        int blockSize = cipher.getBlockSize();
        int paddedLength = ((fileBytes.length + blockSize - 1) / blockSize) * blockSize;
        byte[] paddedBytes = new byte[paddedLength];
        System.arraycopy(fileBytes, 0, paddedBytes, 0, fileBytes.length);

        byte[] encryptedBytes = cipher.doFinal(paddedBytes);

        String encryptedContent = Base64.getEncoder().encodeToString(encryptedBytes);

        Path encryptedFilePath = Paths.get(filePath);
        Files.write(encryptedFilePath, encryptedContent.getBytes(), StandardOpenOption.CREATE);

        return encryptedFilePath.toString();
    }

    public static String decryptFile(String filePath, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");

        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        String encryptedContent = new String(Files.readAllBytes(Paths.get(filePath)));
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedContent);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        Path decryptedFilePath = Paths.get(filePath);
        Files.write(decryptedFilePath, decryptedBytes, StandardOpenOption.CREATE);

        return decryptedFilePath.toString();
    }
}

