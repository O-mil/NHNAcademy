package com.nhnacademy.edu.springframework.messagesender.sender;

import com.nhnacademy.edu.springframework.messagesender.User;

public class SmsMessageSender implements MessageSender {

    public SmsMessageSender() {
        System.out.println("Sms Message Sender를 생성합니다.");
    }

    @Override
    public void sendMessage(User user, String Message) {
        System.out.println("SMS Message Sent to " + user.getPhoneNumber() + " : " + Message);

    }
}
