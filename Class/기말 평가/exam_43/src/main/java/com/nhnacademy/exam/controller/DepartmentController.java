package com.nhnacademy.exam.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.nhnacademy.exam.dto.DepartmentDTO;
import com.nhnacademy.exam.dto.DepartmentModifyDTO;
import com.nhnacademy.exam.exception.DepartmentNotFoundException;
import com.nhnacademy.exam.exception.DuplicateDepartmentException;
import com.nhnacademy.exam.exception.ErrorResponse;
import com.nhnacademy.exam.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    /**
     * 부서 등록
     *
     * @param departmentDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        try {
            DepartmentDTO createdDepartment = departmentService.createdDepartment(departmentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartment);
        } catch (DuplicateDepartmentException e) {
            String errorMessage = e.getMessage() + e.getDepartmentCode();
            ErrorResponse errorResponse = new ErrorResponse(errorMessage, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }


    /**
     * 부서 조회
     *
     * @param departmentCode
     * @return
     */
    @GetMapping("/{departmentCode}")
    public ResponseEntity<?> getDepartment(@PathVariable String departmentCode) {
        try {
            DepartmentDTO departmentDTO = departmentService.getDepartment(departmentCode);
            return ResponseEntity.ok(departmentDTO);
        } catch (DepartmentNotFoundException e) {
            String errorMessage = e.getMessage() + e.getDepartmentCode();
            ErrorResponse errorResponse = new ErrorResponse(errorMessage, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping(value = "/{departmentCode}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> getDepartment(
            @PathVariable String departmentCode,
            @RequestHeader("X-USER-ID") String userId) throws Exception {
        try {
            DepartmentDTO departmentDTO = departmentService.getDepartment(departmentCode);

            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(departmentDTO);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            return ResponseEntity.ok().headers(headers).body(xml);
        } catch (DepartmentNotFoundException e) {
            String errorMessage = e.getMessage() + e.getDepartmentCode();
            ErrorResponse errorResponse = new ErrorResponse(errorMessage, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    /**
     * 부서 수정
     *
     * @param departmentCode
     * @param departmentDTO
     * @return
     */
    @PutMapping("/{departmentCode}")
    public ResponseEntity<String> updateDepartmentName(
            @PathVariable("departmentCode") String departmentCode,
            @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO updateDepartmentName = departmentService.updateDepartmentName(departmentCode, departmentDTO.getDepartmentName());
        String response = updateDepartmentName.toString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return ResponseEntity.ok().headers(headers).body(response);
    }

    /**
     * 부서 삭제
     *
     * @param departmentCode
     * @return
     */
    @DeleteMapping("/{departmentCode}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String departmentCode) {
        departmentService.deleteDepartment(departmentCode);
        return ResponseEntity.noContent().build();
    }
}
