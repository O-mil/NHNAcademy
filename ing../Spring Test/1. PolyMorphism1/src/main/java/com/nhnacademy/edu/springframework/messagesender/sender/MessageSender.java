package com.nhnacademy.edu.springframework.messagesender.sender;

import com.nhnacademy.edu.springframework.messagesender.User;

public interface MessageSender {

  public default void sendMessage(User user, String message) {

  }
}
