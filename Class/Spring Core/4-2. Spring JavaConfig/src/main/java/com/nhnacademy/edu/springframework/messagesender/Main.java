package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.sender.EmailMessageSender;
import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;
import com.nhnacademy.edu.springframework.messagesender.sender.SmsMessageSender;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework.messagesender.config");
        MessageSender service = context.getBean("smsMessageSender", SmsMessageSender.class);
        MessageSender service2 = context.getBean("emailMessageSender", EmailMessageSender.class);

        User user = new User("ghkwjd5343@gmail.com", "010-5883-6775");
        service.sendMessage(user,"hi");
        service2.sendMessage(user, "hi");

        context.close();
    }
}