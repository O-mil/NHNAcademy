package com.nhnacademy.springcore;

import com.nhnacademy.springcore.sender.MessageSender;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageSendService {

    private MessageSender messageSender;

    public MessageSendService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void doSendMessage() {
        User user = new User("hey");
        messageSender.sendMessage(user, "hi");
    }
}
