package com.example.demo;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

    public class AES_CBC_PKCS5Padding {

        public static String encryptFile(String filePath, String key) throws Exception {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Generate a random IV (Initialization Vector)
            SecureRandom random = new SecureRandom();
            byte[] ivBytes = new byte[cipher.getBlockSize()];
            random.nextBytes(ivBytes);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);

            // Convert the key to a SecretKey
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");

            // Initialize the cipher for encryption
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

            // Read the file content
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));

            // Encrypt the file content
            byte[] encryptedBytes = cipher.doFinal(fileBytes);

            // Combine the IV and encrypted content
            byte[] combined = new byte[ivBytes.length + encryptedBytes.length];
            System.arraycopy(ivBytes, 0, combined, 0, ivBytes.length);
            System.arraycopy(encryptedBytes, 0, combined, ivBytes.length, encryptedBytes.length);

            // Encode the combined bytes using Base64
            String encryptedContent = Base64.getEncoder().encodeToString(combined);

            // Write the encrypted content to a new file
            Path encryptedFilePath = Paths.get(filePath);
            Files.write(encryptedFilePath, encryptedContent.getBytes(), StandardOpenOption.CREATE);

            return encryptedFilePath.toString();
        }

        public static String decryptFile(String filePath, String key) throws Exception {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Read the encrypted content from the file
            String encryptedContent = new String(Files.readAllBytes(Paths.get(filePath)));

            // Decode the Base64-encoded content
            byte[] combined = Base64.getDecoder().decode(encryptedContent);

            // Extract the IV and encrypted content
            byte[] ivBytes = new byte[cipher.getBlockSize()];
            byte[] encryptedBytes = new byte[combined.length - ivBytes.length];
            System.arraycopy(combined, 0, ivBytes, 0, ivBytes.length);
            System.arraycopy(combined, ivBytes.length, encryptedBytes, 0, encryptedBytes.length);

            // Convert the key to a SecretKey
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");

            // Initialize the cipher for decryption
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));

            // Decrypt the content
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            // Write the decrypted content to a new file
            Path decryptedFilePath = Paths.get(filePath);
            Files.write(decryptedFilePath, decryptedBytes, StandardOpenOption.CREATE);

            return decryptedFilePath.toString();
        }

        public static String decryptFile(String filePath, String key, String name) throws Exception {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Read the encrypted content from the file
            String encryptedContent = new String(Files.readAllBytes(Paths.get(filePath)));

            // Decode the Base64-encoded content
            byte[] combined = Base64.getDecoder().decode(encryptedContent);

            // Extract the IV and encrypted content
            byte[] ivBytes = new byte[cipher.getBlockSize()];
            byte[] encryptedBytes = new byte[combined.length - ivBytes.length];
            System.arraycopy(combined, 0, ivBytes, 0, ivBytes.length);
            System.arraycopy(combined, ivBytes.length, encryptedBytes, 0, encryptedBytes.length);

            // Convert the key to a SecretKey
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");

            // Initialize the cipher for decryption
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));

            // Decrypt the content
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            // Write the decrypted content to a new file
            Path decryptedFilePath = Paths.get("D:/" + name);
            Files.write(decryptedFilePath, decryptedBytes, StandardOpenOption.CREATE);

            return decryptedFilePath.toString();
        }


    }





