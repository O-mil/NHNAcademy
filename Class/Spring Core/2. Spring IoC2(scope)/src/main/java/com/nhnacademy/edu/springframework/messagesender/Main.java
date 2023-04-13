package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
            MessageSender emailMessageSender1 = context.getBean("emailMessageSender", MessageSender.class);
            MessageSender emailMessageSender2 = context.getBean("emailMessageSender", MessageSender.class);

            MessageSender smsMessageSender1 = context.getBean("smsMessageSender", MessageSender.class);
            MessageSender smsMessageSender2 = context.getBean("smsMessageSender", MessageSender.class);


            User user = new User("ghkwjd5343@gmail.com", "010.5883.6775");
            emailMessageSender1.sendMessage(user, "email Message");
            emailMessageSender2.sendMessage(user, "email Message");

            smsMessageSender1.sendMessage(user, "sms Message");
            smsMessageSender2.sendMessage(user, "sms Message");

            System.out.println(emailMessageSender1.hashCode());
            System.out.println(emailMessageSender2.hashCode());

            System.out.println(smsMessageSender1.hashCode());
            System.out.println(smsMessageSender2.hashCode());
        }
    }
}