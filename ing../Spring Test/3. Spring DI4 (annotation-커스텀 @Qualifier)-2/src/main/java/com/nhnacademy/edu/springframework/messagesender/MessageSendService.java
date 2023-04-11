package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MessageSendService {

    private MessageSender messageSender;

    @Autowired
    public void setSmsMessageSender(@Qualifier("smsMessageSender") MessageSender messageSender) {
        System.out.println("setSmsMessageSender invoked!");
        this.messageSender = messageSender;
    }

    @Autowired
    public void setEmailMessageSender(@Qualifier("emailMessageSender") MessageSender messageSender) {
        System.out.println("setEmailMessageSender invoked!");
        this.messageSender = messageSender;
    }


    public void doSendMessage() {
        User user = new User("ghkwjd5343@gmail.com", "010-5883-6775");
        messageSender.sendMessage(user,   "hi");
    }
}