package com.nhnacademy.edu.springframework.messagesender.sender;

import com.nhnacademy.edu.springframework.messagesender.User;

public class MessageSendService {

    private final MessageSender messageSender;

    public MessageSendService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void doSendMessage() {
        User user = new User("ghkwjd5343@gmail.com", "Hwajeong");
        messageSender.sendMessage(user, "hi");
    }
}
