package com.balance.mail;

/**
 * Created by ttosheva on 02/12/2016.
 */

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import javax.mail.PasswordAuthentication;

public class SendEmail extends Thread {

    private static final String EMAIL_SENDER = "balance.kantar@gmail.com"; // EMAIL ADRESS
    private static final String PASSWORD = "bestjuniorteam"; //EMAIL Password
    private static final String MAIL_PROPERTIES = "mail.properties";
    private String receiverEmail;
    private String message;

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public void setCodeVerification(String codeVerification) {
        message = "Please confirm your registration on this link:\n";
        message += "http://localhost:8080/balance/" + codeVerification;
        message += "\nThank you,\n Balance team";
    }

    private void sendMail() {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(MAIL_PROPERTIES)) {
            props.load(resourceStream);
        } catch (IOException e) {
            return;
        }

        try {
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(EMAIL_SENDER, PASSWORD);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_SENDER));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
            message.setSubject(EMAIL_SENDER);
            message.setText(this.message);
            message.setSentDate(new Date());
            Transport.send(message);
            System.out.println("Email sent");
        } catch (MessagingException e) {
            //todo log
            System.out.println("Couldnt send email");
            return;
        }
    }

    @Override
    public void run() {
        sendMail();
        System.out.println("yey");
    }

}