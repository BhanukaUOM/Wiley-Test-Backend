package com.sadheera.wileytest.service;

import java.io.File;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    
    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendConfirmationMail(String userEmail, String confirmationToken){
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, "utf-8");
            String body = GetHTMLContent("RegisterMailTemplate.html", "https://d2mb2kzywhsi1v.cloudfront.net/auth/confirm-account?token="+ confirmationToken);

            mailMessage.setTo(userEmail);
            mailMessage.setFrom("no-reply@example.com");
            mailMessage.setSubject("Account Activation");
            mailMessage.setText(body, true);
            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean sendPasswordResetMail(String userEmail, String Token){
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, "utf-8");
            String body = GetHTMLContent("PasswordResetMailTemplate.html", "https://d2mb2kzywhsi1v.cloudfront.net/auth/reset-password-verify?email=" + userEmail + "&token="+ Token);

            mailMessage.setTo(userEmail);
            mailMessage.setFrom("no-reply@example.com");
            mailMessage.setSubject("Password Reset");
            mailMessage.setText(body, true);
            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public String GetHTMLContent(String TemplateName, String Link) {
        try {        
            Scanner scanner = new Scanner( new File(System.getProperty("user.dir") + "\\src\\main\\resources\\templates", TemplateName), "UTF-8" );
            String text = scanner.useDelimiter("\\A").next();
            text = text.replaceAll("url_link", Link);
            scanner.close();
            return text;
        } catch (Exception e) {
            return Link;
        }
    }
}