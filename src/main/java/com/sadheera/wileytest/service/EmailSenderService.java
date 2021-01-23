package com.sadheera.wileytest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String userEmail, String confirmationToken){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userEmail);
        mailMessage.setFrom("no-reply@example.com");
        mailMessage.setSubject("Account Activation!");
        mailMessage.setText("http://localhost:3000/auth/confirm-account?token="+ confirmationToken);
        javaMailSender.send(mailMessage);
    }
    
    public boolean sendSimpleMail(String to, String sub, String body){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setFrom("no-reply@example.com");
        mailMessage.setSubject(sub);
        mailMessage.setText(body);
        Boolean isSent = false;
        try
        {
            javaMailSender.send(mailMessage);
            isSent = true;
        }
        catch (Exception e) {}
        
        return isSent;
    }
}