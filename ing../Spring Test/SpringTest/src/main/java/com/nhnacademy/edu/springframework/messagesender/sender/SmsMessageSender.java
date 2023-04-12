package com.nhnacademy.edu.springframework.messagesender.sender;

import com.nhnacademy.edu.springframework.messagesender.User;
import com.nhnacademy.edu.springframework.messagesender.aop.TestAnnotation;

public class SmsMessageSender implements MessageSender {

    public SmsMessageSender() {
        System.out.println("Sms Message Sender를 생성합니다.");
    }

//    public void init() {
//        System.out.println("---------------------- INIT");
//    }
//
//    public void close() {
//        System.out.println("---------------------- CLEANUP");
//    }

    @TestAnnotation
    @Override
    public void sendMessage(User user, String Message) {
        System.out.println("SMS Message Sent to " + user.getPhoneNumber() + " : " + Message);
    }
}
