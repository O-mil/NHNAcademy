package com.nhnacademy.exam.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DepartmentModifyDTO {
    private String departmentName;
}
