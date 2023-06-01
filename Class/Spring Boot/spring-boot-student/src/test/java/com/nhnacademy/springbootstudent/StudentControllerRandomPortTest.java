package com.nhnacademy.springbootstudent;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerRandomPortTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Order(1)
    void testGetStudents() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Student> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Student>> exchange = testRestTemplate.exchange(
                "/students",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Student>>() {
                });

        assertThat(exchange.getBody())
                .contains(new Student(2L, "신짱구", 50));
    }

    @Test
    @Order(2)
    void testGetStudent() throws Exception{
        ResponseEntity<Student> result = testRestTemplate.getForEntity(
                "/students/{id}",
                Student.class,
                1L);

        assertThat(result.getBody())
                .isEqualTo(new Student(1L, "김화정", 70));
    }

    @Test
    @Order(3)
    void testCreateStudent() throws Exception{
        Student cheolsu = new Student(7L, "김철수", 100);
        ResponseEntity<Student> result = testRestTemplate.postForEntity(
                "/students",
                cheolsu,
                Student.class);

        assertThat(result.getBody())
                .isEqualTo(cheolsu);
    }

    @Test
    @Order(4)
    void testDeleteStudent() throws Exception{
        testRestTemplate.delete(
                "/students/{id}",
                7L);
    }


}