package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.User;
import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;

public class MessageSendService {

    private final MessageSender messageSender;

    public MessageSendService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void doSendMessage() {
        User user = new User("hey@naver.com", "010.1234.5678");
        messageSender.sendMessage(user, "hi");
    }
}
