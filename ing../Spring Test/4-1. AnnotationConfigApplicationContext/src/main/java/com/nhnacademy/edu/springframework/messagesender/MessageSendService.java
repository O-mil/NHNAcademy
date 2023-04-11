package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

public class MessageSendService {

    private MessageSender messageSender;

    @Value("${from}")
    private String from;

    @Autowired
    public void setEmailMessageSender(@Qualifier("emailMessageSender") MessageSender messageSender) {
        System.out.println("setEmailMessageSender invoked!");
        this.messageSender = messageSender;
    }


    public void doSendMessage() {
        User user = new User("ghkwjd5343@gmail.com", "010-5883-6775");
        System.out.println("From: " + from);
        messageSender.sendMessage(user,   "hi");
    }
}