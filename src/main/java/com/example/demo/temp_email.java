package com.example.demo;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;

public class temp_email {

        public static void main(String[] args) {
            final String username = "rishirajpatel172@gmail.com";
            final String password = "ojay tcvp coqy vjch";

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("raghav.2022ca072@mnnit.ac.in"));
                message.setSubject("Encryption Guru...");

                //temp
                String local_to_show="Encryption_guyzz";
                InternetAddress fromaddress=new InternetAddress("rishirajpatel172@gmail.com",local_to_show);
                message.setFrom(fromaddress);

                // Create the email body part
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText("File Attached...");

                // Attach the file
//                "path/to/your/file.txt";

                String filePath = "D:/practice/zipfile.zip";
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(new File(filePath));
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(new File(filePath).getName());

                // Create the Multipart object
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                multipart.addBodyPart(attachmentBodyPart);

                // Set the Multipart object as the message content
                message.setContent(multipart);

                // Send the message
                Transport.send(message);

                System.out.println("Email sent successfully!");
                JOptionPane.showMessageDialog(null,"Email has sent Successfully");

            } catch (MessagingException e) {
                JOptionPane.showMessageDialog(null,"Invalid Email");
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }



