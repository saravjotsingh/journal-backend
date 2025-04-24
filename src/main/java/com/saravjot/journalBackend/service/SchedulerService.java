package com.saravjot.journalBackend.service;

import com.saravjot.journalBackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulerService {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

//    @Scheduled(cron = "* * * * * *")
//    private void sendEmail(){
//        System.out.println("Sending email to the user");
//        List<User> users = userService.getAll();
//        System.out.println("Sending message");
////        for (User user : users) {
////            System.out.println(user.getEmail());
////        emailService.sendMessage();
//
////            //send email to user.email for reminder for user.getEmail
////        }
//    }

}
