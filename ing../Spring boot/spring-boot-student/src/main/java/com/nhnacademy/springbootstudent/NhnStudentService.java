package com.nhnacademy.springbootstudent;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NhnStudentService implements StudentService {

    private final StudentRepository studentRepository;

    public NhnStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
}
