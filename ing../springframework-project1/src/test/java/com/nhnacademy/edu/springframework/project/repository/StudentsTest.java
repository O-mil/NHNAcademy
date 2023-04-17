package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentsTest {

    private  CsvStudents csvStudents;
    private Map<Integer, Student> students;

    @BeforeEach
    void setUp() {
        //초기화
        csvStudents = (CsvStudents) CsvStudents.getInstance();
    }

    @Test
    void load() {

        int expectedSize = 15;

        csvStudents.load();
        int actualSize = csvStudents.findAll().size();

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

    @Test
    void merge() {

        // 테스트 데이터 생성 및 merge() 메서드 호출
        Collection<Score> scores = generateTestScores(); // 테스트 데이터 생성
        csvStudents.merge(scores); // merge() 메서드 호출

        if (students == null) {
            students = new HashMap<>();
        }

        // 추가된 학생들의 score 정보를 검증
        for (Score score : scores) {
            int seq = score.getStudentSeq();
            Student student = students.get(seq);
            student.setScore(score);
            assertNotNull(score); // 학생 객체가 추가되었는지 검증
            assertEquals(score.getScore(), student.getScore()); // score 값을 검증
        }

        assertTrue(students.isEmpty()); // 학생 객체가 모두 추가되었는지 검증
    }

    private Collection<Score> generateTestScores() {
        Collection<Score> scores = new ArrayList<>();
        Random random = new Random();

        // 테스트 데이터 생성
        for (int i = 0; i < 10; i++) {
            int studentSeq = i + 1; // 학생 번호는 1부터 시작
            int score = random.nextInt(101); // score 값은 0부터 100까지의 랜덤 값
            scores.add(new Score(studentSeq, score));
        }

        return scores;
    }
}