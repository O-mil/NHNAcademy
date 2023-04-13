package com.nhnacademy.springcore;


import com.nhnacademy.springcore.sender.MessageSender;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
            MessageSender messageSender = context.getBean("doorayMessageSender", MessageSender.class);

            User user = new User("hey");
            messageSender.sendMessage(user, "hi");
        }
    }
}