package com.nhnacademy.edu.springframework.messagesender.config;

import com.nhnacademy.edu.springframework.messagesender.MessageSendService;
import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ImportResource("classpath:/beans.xml")
public class MainConfig {

    //방법 1
//    @Autowired
//    @Qualifier("smsMessageSender")
//    private MessageSender smsMessageSender;
//
//
//    @Bean
//    public MessageSendService messageSendService() {
//        return new MessageSendService(smsMessageSender);
//    }

    //방법 2
    @Autowired
    private MessageSenderConfig messageSenderConfig;


    @Bean
    public MessageSendService messageSendService() {
        return new MessageSendService(messageSenderConfig.smsMessageSender());
    }

}


