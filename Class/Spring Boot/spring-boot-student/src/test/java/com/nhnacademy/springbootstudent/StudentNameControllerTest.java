package com.nhnacademy.springbootstudent;

import com.nhnacademy.springbootstudent.configuration.StudentProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(StudentNameController.class)
class StudentNameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentProperties studentProperties;

    @Test
    void TestGetStudentName() throws Exception {
        given(studentProperties.getName())
                .willReturn("신짱구");
        this.mockMvc.perform(get("/student-name2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8)))
                .andExpect(content().string("신짱구"));
    }

}