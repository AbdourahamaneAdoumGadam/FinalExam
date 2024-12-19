package com.taskmgtsys1.taskmanagmentsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String toEmail, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("abdramanadamgadam@gmail.com");
        message.setText("To reset your password, click the link below:\n" + "http://localhost:3000/reset-password?token=" + token);
        mailSender.send(message);
    }
}

