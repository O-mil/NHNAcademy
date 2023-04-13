package com.nhnacademy.edu.springframework.messagesender.sender;

import com.nhnacademy.edu.springframework.messagesender.User;

public class EmailMessageSender implements MessageSender {

    public EmailMessageSender() {
        System.out.println("email Message Sender를 생성합니다.");
    }

    public void init() {
        System.out.println("---------------------- INIT");
    }

    // close를 쓰면 destroyMethod를 따로 설정해주지 않아도 자동으로 끝날 떄 출력됨
    public void close() {
        System.out.println("---------------------- CLEANUP");

    }

    @Override
    public void sendMessage(User user, String message) {
        System.out.println("Email Message sent to " + user.getEmail() + " : " + message);
    }
}
