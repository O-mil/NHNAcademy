package com.nhnacademy.springcore.config;

import com.nhnacademy.springcore.MessageSendService;
import com.nhnacademy.springcore.sender.DoorayMessageSender;
import com.nhnacademy.springcore.sender.MessageSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.nhnacademy.springcore")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DoorayConfig {

    @Bean
    public MessageSender doorayMessageSender() {return new DoorayMessageSender();}

    @Bean
    public MessageSendService messageSendService( MessageSender messageSender) {
        return new MessageSendService(messageSender);
    }
}
