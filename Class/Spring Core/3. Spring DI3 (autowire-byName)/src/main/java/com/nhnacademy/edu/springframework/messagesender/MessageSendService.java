package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;

public class MessageSendService {

    // final keyword 이므로 객체를 생성한뒤 greeter 변수에 값을 할당할 수 없다.
    // private final MessageSender messageSender;
    private MessageSender messageSender;

    // Setter Injection 기본 생성자가 필요하다.
    public MessageSendService() {}

    public void setSmsMessageSender(MessageSender messageSender) {
        System.out.println("setSmsMessageSender invoked!");
        this.messageSender = messageSender;
    }

    public void doSendMessage() {
        User user = new User("hey@naver.com", "010.1234.5678");
        messageSender.sendMessage(user,   "hi");
    }
}