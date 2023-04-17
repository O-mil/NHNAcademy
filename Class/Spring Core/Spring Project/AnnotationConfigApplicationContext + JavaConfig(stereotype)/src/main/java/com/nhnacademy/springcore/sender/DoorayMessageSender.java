package com.nhnacademy.springcore.sender;

import com.nhn.dooray.client.DoorayHook;
import com.nhnacademy.springcore.User;
import com.nhnacademy.springcore.annotation.TimeAnnotation;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DoorayMessageSender implements MessageSender {

    public DoorayMessageSender() {
        System.out.println("dooray Message Sender를 생성합니다.");
    }

    @TimeAnnotation
    @Override
    public boolean sendMessage(User user, String message) {
        new DoorayHookSender(new RestTemplate(), "https://hook.dooray.com/services/3204376758577275363/3514081992077299709/HOTTporbR6CBAVodltB-Dw")
                .send(DoorayHook.builder()
                        .botName(user.getName())
                        .text(message)
                        .build());

        System.out.println("메시지를 보냈습니다.");
        return true;
    }
}
