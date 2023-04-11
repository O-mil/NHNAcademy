package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;

public class MessageSendService {

    private final MessageSender messageSender;

    // 생성자 주입 방식을 사용하므로, 주입 대상 스프링빈에 적절한 생성자가 필요하다.
    public MessageSendService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void doSendMessage() {
        User user = new User("ghkwjd5343@gmail.com", "010-5883-6775");
        //인터페이스의 메소드를 호출하지만 실제 구현 객체의 메소드가 실행됩니다.
        messageSender.sendMessage(user,   "hi");
    }
}
