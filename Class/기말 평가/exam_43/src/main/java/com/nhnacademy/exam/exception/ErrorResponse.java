package com.nhnacademy.exam.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private String title;
    private int status;
    private LocalDateTime timestamp;

    public ErrorResponse(String title, int status, LocalDateTime timestamp) {
        this.title = title;
        this.status = status;
        this.timestamp = timestamp;
    }
}

