package com.nhnacademy.springcore;

import com.nhnacademy.springcore.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MessageSendService {

    private MessageSender messageSender;

    @Autowired
    public MessageSendService(@Qualifier("doorayMessageSender") MessageSender messageSender) {
        System.out.println("MessageSender invoked!");
        this.messageSender = messageSender;
    }
    public void doSendMessage() {
        User user = new User("hey");
        messageSender.sendMessage(user, "hi");
    }
}
