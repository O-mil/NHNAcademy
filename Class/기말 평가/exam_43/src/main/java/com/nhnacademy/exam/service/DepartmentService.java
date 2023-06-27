package com.nhnacademy.exam.service;

import com.nhnacademy.exam.dto.DepartmentDTO;
import com.nhnacademy.exam.entity.Department;
import com.nhnacademy.exam.exception.DepartmentNotFoundException;
import com.nhnacademy.exam.exception.DuplicateDepartmentException;
import com.nhnacademy.exam.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    /**
     * 부서 등록
     *
     * @param departmentDTO
     * @return
     */
    public DepartmentDTO createdDepartment(DepartmentDTO departmentDTO) throws DuplicateDepartmentException {

        if (departmentRepository.existsByDepartmentCode(departmentDTO.getDepartmentCode())) {
            throw new DuplicateDepartmentException("부서 아이디 중복 : ", departmentDTO.getDepartmentCode());
        }

        Department department = new Department(departmentDTO.getDepartmentCode(), departmentDTO.getDepartmentName());

        Department savedDepartment = departmentRepository.save(department);

        return new DepartmentDTO(savedDepartment.getDepartmentCode(), savedDepartment.getDepartmentName());
    }

    /**
     * 부서 조회
     *
     * @param departmentCode
     * @return
     */
    public DepartmentDTO getDepartment(String departmentCode) {
        Department department = departmentRepository.findById(departmentCode)
                .orElseThrow(() -> new DepartmentNotFoundException("department not found : ", departmentCode));

        return new DepartmentDTO(
                department.getDepartmentCode(),
                department.getDepartmentName()
        );
    }

    /**
     * 부서 수정
     *
     * @param departmentCode
     * @param newDepartmentName
     * @return
     */
    public DepartmentDTO updateDepartmentName(String departmentCode, String newDepartmentName) {
        Department department = departmentRepository.findById(departmentCode)
                .orElseThrow(() -> new IllegalArgumentException("departmentCode Not Found"));

        department.setDepartmentName(newDepartmentName);
        Department updateDepartmentName = departmentRepository.save(department);

        return new DepartmentDTO(
                department.getDepartmentCode(),
                department.getDepartmentName()
        );
    }

    /**
     * 부서 삭제
     *
     * @param departmentCode
     */
    public void deleteDepartment(String departmentCode) {
        Department department = departmentRepository.findById(departmentCode)
                .orElseThrow(() -> new IllegalArgumentException("departmentCode Not Found"));

        departmentRepository.delete(department);
    }
}
