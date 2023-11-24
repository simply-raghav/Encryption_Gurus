package com.example.demo;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.Scanner;

public class FolderEncryptor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the encryption key from the user
        System.out.print("Enter encryption key: ");
        String key = scanner.nextLine();

        // Get the folder path from the user
        System.out.print("Enter folder path: ");
        String folderPath = scanner.nextLine();

        try {
            // Generate a secret key from the user-provided key
            SecretKey secretKey = generateSecretKey(key);

            // Encrypt the folder
           // encryptFolder(folderPath, secretKey);

            // Decrypt the folder (for demonstration purposes)
            decryptFolder(folderPath, secretKey);

            System.out.println("Folder encryption and decryption completed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static SecretKey generateSecretKey(String key) throws Exception {
        // Use a KeyGenerator to generate a secret key based on the user-provided key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // 128-bit key size for AES
        return new SecretKeySpec(key.getBytes(), "AES");
    }

    private static void encryptFolder(String folderPath, SecretKey secretKey) throws Exception {
        Files.walkFileTree(Path.of(folderPath), EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                // Encrypt each file in the folder
                try {
                    encryptFile(file.toFile(), secretKey);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static void decryptFolder(String folderPath, SecretKey secretKey) throws Exception {
        Files.walkFileTree(Path.of(folderPath), EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                // Decrypt each file in the folder (for demonstration purposes)
                try {
                    decryptFile(file.toFile(), secretKey);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static void encryptFile(File file, SecretKey secretKey) throws Exception {
        // Initialize Cipher for encryption
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Create an encrypted output stream
        try (FileInputStream fis = new FileInputStream(file);
             FileOutputStream fos = new FileOutputStream(file.getAbsolutePath() + ".encrypted");
             CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {

            // Encrypt the file
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, bytesRead);
            }
        }

        // Delete the original file after encryption (optional, depending on your requirements)
        file.delete();
    }

    private static void decryptFile(File file, SecretKey secretKey) throws Exception {
        // Initialize Cipher for decryption
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Create a decrypted output stream
        try (FileInputStream fis = new FileInputStream(file);
             CipherInputStream cis = new CipherInputStream(fis, cipher);
             FileOutputStream fos = new FileOutputStream(file.getAbsolutePath().replace(".encrypted", ""))) {

            // Decrypt the file
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = cis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }

        // Delete the encrypted file after decryption (optional, depending on your requirements)
        file.delete();
    }
}
