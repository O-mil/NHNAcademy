package com.nhnacademy.exam.exception;

import lombok.Getter;

@Getter
public class MissingParameterException extends RuntimeException {

    public MissingParameterException(String message) {
        super(message);
    }
}
