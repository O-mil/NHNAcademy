package com.nhnacademy.springbootstudent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    StudentRepository studentRepository;

    @Test
    void testFindAll() throws Exception {
        Student 유리 = new Student(7L, "이유리", 70);

        this.testEntityManager.merge(유리);
        Student actual = studentRepository.findById(7L).orElse(null);

        assertThat(actual).isEqualTo(유리);
    }

}