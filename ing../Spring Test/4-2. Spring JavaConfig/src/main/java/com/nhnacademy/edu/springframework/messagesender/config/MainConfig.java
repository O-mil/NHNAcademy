package com.nhnacademy.edu.springframework.messagesender.config;

import com.nhnacademy.edu.springframework.messagesender.MessageSendService;
import com.nhnacademy.edu.springframework.messagesender.sender.EmailMessageSender;
import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;
import com.nhnacademy.edu.springframework.messagesender.sender.SmsMessageSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
//@ImportResource("classpath:/beans.xml")
public class MainConfig {

    @Bean(initMethod = "init")
    public MessageSender smsMessageSender() {
        return new SmsMessageSender();
    }

    @Bean(initMethod = "init")
    public MessageSender emailMessageSender() {
        return new EmailMessageSender();
    }

}


