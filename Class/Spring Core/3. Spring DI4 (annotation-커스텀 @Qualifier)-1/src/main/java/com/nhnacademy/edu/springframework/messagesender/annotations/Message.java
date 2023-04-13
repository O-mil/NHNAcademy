package com.nhnacademy.edu.springframework.messagesender.annotations;

import org.springframework.beans.factory.annotation.Qualifier;


@Qualifier
public enum Message {
    EMAIL, SMS
}
