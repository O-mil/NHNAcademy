package com.nhnacademy.exam.dto;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DepartmentDTO {
    private String departmentCode;
    private String departmentName;

}
