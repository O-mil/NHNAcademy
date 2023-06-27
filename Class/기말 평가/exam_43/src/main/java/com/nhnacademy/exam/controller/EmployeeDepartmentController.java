package com.nhnacademy.exam.controller;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.nhnacademy.exam.dto.DepartmentDTO;
import com.nhnacademy.exam.dto.EmployeeDepartmentDTO;
import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.exception.DepartmentNotFoundException;
import com.nhnacademy.exam.exception.ErrorResponse;
import com.nhnacademy.exam.exception.MissingParameterException;
import com.nhnacademy.exam.service.EmployeeDepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/department-members")
@RequiredArgsConstructor
public class EmployeeDepartmentController {

    private final EmployeeDepartmentService employeeDepartmentService;

    /**
     * 부서 코드로 멤버 조회
     *
     * @param departmentCode
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getEmployeeDepartment(
            @RequestParam(value = "departmentCode", required = false) List<String> departmentCode) {
        try {
            List<EmployeeDepartmentDTO> employeeDepartmentDTOs = employeeDepartmentService.getEmployeeDepartment(departmentCode);
            return ResponseEntity.ok(employeeDepartmentDTOs);
        } catch (MissingParameterException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
