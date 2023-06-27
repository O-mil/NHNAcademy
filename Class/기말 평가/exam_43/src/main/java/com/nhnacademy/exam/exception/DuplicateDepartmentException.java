package com.nhnacademy.exam.exception;

import lombok.Getter;

@Getter
public class DuplicateDepartmentException extends RuntimeException {
    private String departmentCode;

    public DuplicateDepartmentException(String message, String departmentCode) {
        super(message);
        this.departmentCode = departmentCode;
    }
}