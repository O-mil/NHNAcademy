package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;

public class MessageSendService {

    // final keyword 이므로 객체를 생성한뒤 greeter 변수에 값을 할당할 수 없다.
    // private final MessageSender messageSender;
    private MessageSender messageSender;

    // Setter Injection 기본 생성자가 필요하다.
    public MessageSendService() {

    }

    public void setMessageSender(MessageSender messageSender) {
        System.out.println("setMessageSender invoked!");
        this.messageSender = messageSender;
    }

    public void doSendMessage() {
        User user = new User("ghkwjd5343@gmail.com", "010-5883-6775");
        messageSender.sendMessage(user,   "hi");
    }
}