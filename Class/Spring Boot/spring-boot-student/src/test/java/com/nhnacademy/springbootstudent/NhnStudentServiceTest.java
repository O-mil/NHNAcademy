package com.nhnacademy.springbootstudent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest //통합테스트
class NhnStudentServiceTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void testgetStudent() {
        Student kjh = new Student(1L, "김주호", 99);
        studentRepository.save(kjh);

        Optional<Student> actual = studentRepository.findById(1L);
        assertThat(actual).isPresent();
        assertThat(actual.get()).isEqualTo(kjh);
    }
}