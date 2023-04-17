package com.nhnacademy.springcore.sender;

import com.nhnacademy.springcore.User;

public interface MessageSender {

    boolean sendMessage(User user, String message);
}
