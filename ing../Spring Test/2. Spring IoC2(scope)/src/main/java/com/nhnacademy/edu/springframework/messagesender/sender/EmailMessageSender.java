package com.nhnacademy.edu.springframework.messagesender.sender;

import com.nhnacademy.edu.springframework.messagesender.User;

public class EmailMessageSender implements MessageSender {

    public EmailMessageSender() {
        System.out.println("email Message Sender를 생성합니다.");
    }
    @Override
    public void sendMessage(User user, String message) {
        System.out.println("Email Message sent to " + user.getEmail() + " : " + message);

    }
}
