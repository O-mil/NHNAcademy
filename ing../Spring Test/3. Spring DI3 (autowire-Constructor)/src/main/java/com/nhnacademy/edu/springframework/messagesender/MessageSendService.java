package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;

public class MessageSendService {

    // final keyword 이므로 객체를 생성한뒤 greeter 변수에 값을 할당할 수 없다.
     private final MessageSender smsMessageSender;
    private final MessageSender emailMessageSender;

//    private MessageSender messageSender;

    // Setter Injection 기본 생성자가 필요하다.
    public MessageSendService(MessageSender smsMessageSender, MessageSender emailMessageSender) {
        this.smsMessageSender = smsMessageSender;
        this.emailMessageSender = emailMessageSender;
    }

    public void setSmsMessageSender(MessageSender smsMessageSender) {
        System.out.println("setSmsMessageSender invoked!");
    }

    public void setEmailMessageSender(MessageSender emailMessageSender) {
        System.out.println("setSmsMessageSender invoked!");
    }

    public void doSendMessage() {
        User user = new User("hey@naver.com", "010.1234.5678");
        smsMessageSender.sendMessage(user,   "hi");
        emailMessageSender.sendMessage(user,   "hi");

    }
}