package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        //ClassPathXmlApplicationContext: Spring Container -> getBean을 가지고 있음.
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
            MessageSender emailMessageSender = context.getBean("emailMessageSender", MessageSender.class);
            MessageSender smsMessageSender = context.getBean("smsMessageSender", MessageSender.class);

            User user = new User("ghkwjd5343@gmail.com", "010.5883.6775");
            emailMessageSender.sendMessage(user, "email message");
            smsMessageSender.sendMessage(user, "sms message");
        }
    }
}