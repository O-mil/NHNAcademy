package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {

    private CsvStudents csvStudents;

    @BeforeEach
    void setUp() {
        //초기화
        csvStudents = (CsvStudents) CsvStudents.getInstance();
    }

    @Test
    void load() {

        //예상한 데이터 로드 결과
        int expectedSize = 15;

        //실제 데이터 로드 결과
        csvStudents.load();
        int actualSize = csvStudents.findAll().size();

        //예상한 결과와 실제 결과 비교
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void findAll() {
        int expectedSize = 15;

        csvStudents.load();
        Collection<Student> students = csvStudents.findAll();
        int actualSize = students.size();

        assertEquals(expectedSize, actualSize);
    }
}