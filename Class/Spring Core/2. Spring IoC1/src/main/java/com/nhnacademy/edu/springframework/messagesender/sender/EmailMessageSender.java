package com.nhnacademy.edu.springframework.messagesender.sender;

import com.nhnacademy.edu.springframework.messagesender.User;

public class EmailMessageSender implements MessageSender {
    @Override
    public void sendMessage(User user, String message) {
        System.out.println("Email Message sent to " + user.getEmail() + " : " + message);

    }
}
