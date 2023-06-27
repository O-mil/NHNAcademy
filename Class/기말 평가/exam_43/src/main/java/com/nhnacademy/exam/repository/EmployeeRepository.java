package com.nhnacademy.exam.repository;

import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByEmployeeDepartmentsDepartment(Department department);

}
