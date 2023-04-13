package com.nhnacademy.edu.springframework.messagesender;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {

            MessageSendService service = context.getBean("messageSendService", MessageSendService.class);
            service.doSendMessage();
        }
    }
}