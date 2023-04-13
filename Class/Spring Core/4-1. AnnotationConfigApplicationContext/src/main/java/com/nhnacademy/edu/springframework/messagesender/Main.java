package com.nhnacademy.edu.springframework.messagesender;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework.messagesender.config");
        MessageSendService service = context.getBean("messageSendService", MessageSendService.class);
        service.doSendMessage();
    }
}