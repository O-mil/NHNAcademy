package com.nhnacademy.exam.exception;

import lombok.Getter;

@Getter
public class DepartmentNotFoundException extends RuntimeException{
    private String departmentCode;

    public DepartmentNotFoundException(String message, String departmentCode) {
        super(message);
        this.departmentCode = departmentCode;
    }
}
