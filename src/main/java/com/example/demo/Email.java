package com.example.demo;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

    public class Email {

        Session newSession=null;
        MimeMessage mimeMessage=null;


        void sendEmail() throws MessagingException {
            String fromuser="rishirajpatel172@gmail.com";
            String fromuserpassword="ojay tcvp coqy vjch";
            String emailHost="smtp.gmail.com";
            Transport transport=newSession.getTransport("smtp");
            transport.connect(emailHost,fromuser,fromuserpassword);
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
            transport.close();
            System.out.println("Email has sent successfully");
        }

        MimeMessage draftEmail(String realotp) throws AddressException, MessagingException, UnsupportedEncodingException {
            String[] emailRecepients={"rishirajpatel173@gmail.com","abhishek.2022ca006@mnnit.ac.in","raghav.2022ca072@mnnit.ac.in"};
            String email_subject="Alert Alert Alert.....Its for Trial Purpose";

//            int min=1000;
//            int max=10000;
//
//            double otp=Math.random()*(max-min+1)+min;
//            int realotp=(int)otp;
//            // What is your OTP
//            System.out.println(realotp);

            String email_body="Your OTP : "+realotp;
            mimeMessage=new MimeMessage(newSession);

            String local_to_show="Encryption_guyzz";
            InternetAddress fromaddress=new InternetAddress("rishirajpatel172@gmail.com",local_to_show);
            mimeMessage.setFrom(fromaddress);

            for(int i=0;i<emailRecepients.length;i++){
                mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(emailRecepients[i])));
            }
            mimeMessage.setSubject(email_subject);
            MimeMultipart multiPart=new MimeMultipart();
            MimeBodyPart bodyPart=new MimeBodyPart();
            bodyPart.setContent(email_body,"text/html");
            multiPart.addBodyPart(bodyPart);
            mimeMessage.setContent(multiPart);
            return mimeMessage;
        }

        void setupServerProperties() {
            Properties properties=new Properties();
            properties.put("mail.smtp.port","587");
            properties.put("mail.smtp.auth","true");
            properties.put("mail.smtp.starttls.enable","true");
            newSession = Session.getDefaultInstance(properties,null);
        }
    }


