package com.nhnacademy.edu.springframework.messagesender.sender;

import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.edu.springframework.messagesender.User;
import org.springframework.web.client.RestTemplate;

public class SmsMessageSender implements MessageSender {

    public SmsMessageSender() {
        System.out.println("Sms Message Sender를 생성합니다.");
    }

    @Override
    public void sendMessage(User user, String Message) {
        new DoorayHookSender(new RestTemplate(), "https://hook.dooray.com/services/3204376758577275363/3514081992077299709/HOTTporbR6CBAVodltB-Dw")
                .send(DoorayHook.builder()
                        .botName("hey")
                        .text("hi!!!!!")
                        .build());
    }
}
