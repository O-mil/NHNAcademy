package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

public class MessageSendService {

    private MessageSender messageSender;

    @Autowired
    public void setEmailMessageSender(@Qualifier("emailMessageSender") MessageSender messageSender) {
        System.out.println("setEmailMessageSender invoked!");
        this.messageSender = messageSender;
    }


    public void doSendMessage() {
        User user = new User("hey@naver.com", "010.1234.5678");
        messageSender.sendMessage(user,   "hi");
    }
}