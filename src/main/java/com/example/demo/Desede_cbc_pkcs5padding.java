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

public class Desede_cbc_pkcs5padding {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the file path: ");
            String filePath = scanner.nextLine();

            System.out.print("Enter the DESede key (24 characters): ");
            String key = scanner.nextLine();

            // Encrypt the file
            String encryptedFilePath = encryptFileDESede(filePath, key);
            System.out.println("File encrypted successfully. Encrypted file: " + encryptedFilePath);

            // Decrypt the file
            String decryptedFilePath = decryptFileDESede(encryptedFilePath, key);
            System.out.println("File decrypted successfully. Decrypted file: " + decryptedFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encryptFileDESede(String filePath, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "DESede");
        IvParameterSpec iv = new IvParameterSpec(new byte[8]);

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
        byte[] encryptedBytes = cipher.doFinal(fileBytes);

        // Combine IV and encrypted content
        byte[] ivAndEncrypted = new byte[iv.getIV().length + encryptedBytes.length];
        System.arraycopy(iv.getIV(), 0, ivAndEncrypted, 0, iv.getIV().length);
        System.arraycopy(encryptedBytes, 0, ivAndEncrypted, iv.getIV().length, encryptedBytes.length);

        // Encode the combined content using Base64
        String encryptedContent = Base64.getEncoder().encodeToString(ivAndEncrypted);

        Path encryptedFilePath = Paths.get(filePath);
        Files.write(encryptedFilePath, encryptedContent.getBytes(), StandardOpenOption.CREATE);

        return encryptedFilePath.toString();
    }

    public static String decryptFileDESede(String filePath, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "DESede");

        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));

        // Decode the Base64 content
        byte[] ivAndEncrypted = Base64.getDecoder().decode(fileBytes);

        // Extract IV and ciphertext
        byte[] ivBytes = new byte[8];
        byte[] encryptedBytes = new byte[ivAndEncrypted.length - 8];
        System.arraycopy(ivAndEncrypted, 0, ivBytes, 0, 8);
        System.arraycopy(ivAndEncrypted, 8, encryptedBytes, 0, encryptedBytes.length);

        IvParameterSpec iv = new IvParameterSpec(ivBytes);

        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

        // Decrypt the content
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        Path decryptedFilePath = Paths.get(filePath);
        Files.write(decryptedFilePath, decryptedBytes, StandardOpenOption.CREATE);

        return decryptedFilePath.toString();
    }
}
