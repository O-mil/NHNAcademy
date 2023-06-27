package com.nhnacademy.exam.service;


import com.nhnacademy.exam.dto.DepartmentDTO;
import com.nhnacademy.exam.dto.EmployeeDTO;
import com.nhnacademy.exam.dto.EmployeeDepartmentDTO;
import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.entity.Employee;
import com.nhnacademy.exam.exception.DepartmentNotFoundException;
import com.nhnacademy.exam.exception.MissingParameterException;
import com.nhnacademy.exam.repository.DepartmentRepository;
import com.nhnacademy.exam.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeDepartmentService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    /**
     * 부서코드로 멤버 조회
     *
     * @param departmentCodes
     * @return
     */
    public List<EmployeeDepartmentDTO> getEmployeeDepartment(List<String> departmentCodes) {

        if (departmentCodes == null || departmentCodes.isEmpty()) {
            throw new MissingParameterException("Required request parameter 'departmentIds' for method parameter type String is not present");
        }
        List<EmployeeDepartmentDTO> employeeDepartmentDTOs = new ArrayList<>();
        for (String departmentCode: departmentCodes) {
            Department department = departmentRepository.findByDepartmentCode(departmentCode);
            if (department != null) {
                List<Employee> employees = employeeRepository.findByEmployeeDepartmentsDepartment(department);
                for (Employee employee: employees) {
                    DepartmentDTO departmentDTO = new DepartmentDTO(department.getDepartmentCode(), department.getDepartmentName());
                    EmployeeDTO employeeDTO = new EmployeeDTO(employee.getEmployeeId(), employee.getEmployeeName());
                    EmployeeDepartmentDTO employeeDepartmentDTO = new EmployeeDepartmentDTO(departmentDTO, employeeDTO);
                    employeeDepartmentDTOs.add(employeeDepartmentDTO);
                }
            }
        }
        return employeeDepartmentDTOs;
    }
}
