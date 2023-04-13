package com.nhnacademy.edu.springframework.messagesender.sender;

import com.nhnacademy.edu.springframework.messagesender.User;

public class SmsMessageSender implements MessageSender {

    public SmsMessageSender() {
        System.out.println("Sms Message Sender를 생성합니다.");
    }

    public void init() {
        System.out.println("Init--------------------------------------------------");
    }

    public void destroy() {
        System.out.println("SMS Destory--------------------------------------------------");
    }

    @Override
    public void sendMessage(User user, String Message) {
        System.out.println("SMS Message Sent to " + user.getPhoneNumber() + " : " + Message);

    }
}
