package com.nhnacademy.springbootstudent;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Test
    @Order(1)
    void testGetStudents() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", equalTo("김화정")));
    }

    @Test
    @Order(2)
    void testGetStudent() throws Exception{

        mockMvc.perform(get("/students/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", equalTo("김화정")));
    }

    @Test
    @Order(3)
    void testCreateStudent() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Student cheolsu = new Student(7L, "김철수", 100);
        mockMvc.perform(post("/students")
                        .content(objectMapper.writeValueAsString(cheolsu))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", equalTo("김철수")));
    }


    @Test
    @Order(4)
    void deleteStudent() throws Exception{
        this.mockMvc.perform(delete("/students/{id}", 7L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(jsonPath("$.result", equalTo("OK")));
    }



    //위에 1, 2, 3, 4 방식이나 아래 방식 사용
//    @Test
//    void test(@Autowired TestRestTemplate restTemplate) throws Exception {
//        String body = restTemplate.getForObject("/students", String.class);
//        System.out.println(body);
//    }
}