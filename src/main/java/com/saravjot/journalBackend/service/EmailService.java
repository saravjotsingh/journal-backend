package com.saravjot.journalBackend.service;

import io.awspring.cloud.ses.SimpleEmailServiceMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;

@Service
public class EmailService {

    @Autowired
    private SimpleEmailServiceMailSender mailSender;

    public void sendMessage() {
        System.out.println("Sending email sendMessage");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("saravjotpabla@gmail.com");
        message.setSubject("hello");
        message.setText("hi");
        message.setFrom("support@saravjotsingh.com");

        mailSender.send(message);
    }
}
