package com.nhnacademy.edu.springframework.messagesender.config;

import com.nhnacademy.edu.springframework.messagesender.MessageSendService;
import com.nhnacademy.edu.springframework.messagesender.sender.EmailMessageSender;
import com.nhnacademy.edu.springframework.messagesender.sender.MessageSender;
import com.nhnacademy.edu.springframework.messagesender.sender.SmsMessageSender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

//    //방법 1
    @Bean
    public MessageSendService messageSendService(@Qualifier("emailMessageSender") MessageSender messageSender) {
        return new MessageSendService(messageSender);
    }

    //방법 2
//    @Bean
//    public MessageSendService messageSendService(MessageSender emailMessageSender) {
//        return new MessageSendService(emailMessageSender);
//    }

    //방법 3
//    @Bean
//    public MessageSendService messageSendService() {
//        return new MessageSendService(emailMessageSender());
//    }


}


