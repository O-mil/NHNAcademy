package com.nhnacademy.edu.springframework.messagesender.sender;

import com.nhnacademy.edu.springframework.messagesender.User;

public class EmailMessageSender implements MessageSender {

    public void SendMessage(User user, String Message) {
        System.out.println("SMS Message Sent to " + user.getPhoneNumber() + " : " + Message);

    }
}
