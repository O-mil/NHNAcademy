package com.nhnacademy.exam.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeDTO {

    private Long employeeId;
    private String employeeName;
}
