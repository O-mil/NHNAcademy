package com.nhnacademy.edu.springframework.messagesender;

import com.nhnacademy.edu.springframework.messagesender.sender.EmailMessageSender;
import com.nhnacademy.edu.springframework.messagesender.sender.MessageSendService;
import com.nhnacademy.edu.springframework.messagesender.sender.SmsMessageSender;

public class Main {
    public static void main(String[] args) {
        SmsMessageSender smsMessageSender = new SmsMessageSender();
        MessageSendService messageSendService = new MessageSendService(smsMessageSender);
        messageSendService.doSendMessage();

        EmailMessageSender emailMessageSender = new EmailMessageSender();
        messageSendService = new MessageSendService(emailMessageSender);
        messageSendService.doSendMessage();
    }
}