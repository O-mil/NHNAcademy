package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;

public class MessageSendService {

    private MessageSender messageSender;

    public MessageSendService() {

    }

    public void setMessageSender(MessageSender messageSender) {
        System.out.println("setMessageSender invoked!");
        this.messageSender = messageSender;
    }

    public void doSendMessage() {
        User user = new User("ghkwjd5343@gmail.com", "010-5883-6775");
        messageSender.sendMessage(user,   "hi");
    }
}
