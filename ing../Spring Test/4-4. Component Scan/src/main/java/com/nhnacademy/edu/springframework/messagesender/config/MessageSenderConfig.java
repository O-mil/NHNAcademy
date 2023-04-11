package com.nhnacademy.edu.springframework.messagesender.config;

import com.nhnacademy.edu.springframework.messagesender.sender.EmailMessageSender;
import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;
import com.nhnacademy.edu.springframework.messagesender.sender.SmsMessageSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Controller
@Service
@Repository
@Configuration
@ComponentScan(basePackages = "com.nhnacademy.edu.springframework.messagesender")
public class MessageSenderConfig {
    @Bean(initMethod = "init")
    public MessageSender smsMessageSender() {
        return new SmsMessageSender();
    }

    @Bean(initMethod = "init")
    public MessageSender emailMessageSender() {
        return new EmailMessageSender();
    }
}
