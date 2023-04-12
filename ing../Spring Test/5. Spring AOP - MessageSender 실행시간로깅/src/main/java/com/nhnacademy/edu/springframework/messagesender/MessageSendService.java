package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

// 별도로 지정하지 않는 이상은 messageSendService로 설정됨.
//지정하려면 @Service()안에 지정해주기 -> Spring Bean 이름
//@Component()도 같음
@Service ("messageSendService")
@PropertySource("classpath:name.properties")
public class MessageSendService {

    private final MessageSender messageSender;
    @Value("${name}")
    private String name;

    @Autowired
    public MessageSendService(@Qualifier("smsMessageSender") MessageSender messageSender) {
//        System.out.println("MessageSender invoked!");
        this.messageSender = messageSender;
    }


    public void doSendMessage() {
        User user = new User("hey@naver.com", "010.1234.5678");
        messageSender.sendMessage(user,   "hi! " + name);
    }
}