package com.nhnacademy.exam.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "employee_department")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeDepartment {
    @Id
    @Column(name = "employee_department_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeDepartmentId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "department_code")
    private Department department;
}
