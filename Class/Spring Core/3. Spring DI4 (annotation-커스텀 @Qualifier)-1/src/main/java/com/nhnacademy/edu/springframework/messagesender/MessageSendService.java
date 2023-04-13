package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.annotations.Message;
import com.nhnacademy.edu.springframework.messagesender.annotations.MessageQualifier;
import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageSendService {

    private MessageSender messageSender;

    @Autowired
    public void setSmsMessageSender(@MessageQualifier(message = Message.SMS, dummy = false) MessageSender messageSender) {
        System.out.println("setSmsMessageSender invoked!");
        this.messageSender = messageSender;
    }

    @Autowired
    public void setEmailMessageSender(@MessageQualifier(message = Message.EMAIL, dummy = false) MessageSender messageSender) {
        System.out.println("setEmailMessageSender invoked!");
        this.messageSender = messageSender;
    }


    public void doSendMessage() {
        User user = new User("hey@naver.com", "010.1234.5678");
        messageSender.sendMessage(user,   "hi");
    }
}