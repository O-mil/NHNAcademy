package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.sender.EmailMessageSender;
import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;
import com.nhnacademy.edu.springframework.messagesender.sender.SmsMessageSender;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework.messagesender.config");

        MessageSendService messageSendService = context.getBean("messageSendService", MessageSendService.class);
        messageSendService.doSendMessage();

        context.close();
    }
}