package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageSendService {

    private final MessageSender messageSender;

    @Autowired
    public MessageSendService(@Qualifier("smsMessageSender") MessageSender messageSender) {
        System.out.println("MessageSender invoked!");
        this.messageSender = messageSender;
    }


    public void doSendMessage() {
        User user = new User("ghkwjd5343@gmail.com", "010-5883-6775");
        messageSender.sendMessage(user,   "hi");
    }
}