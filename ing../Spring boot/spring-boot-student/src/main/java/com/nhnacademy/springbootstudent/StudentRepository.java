package com.nhnacademy.springbootstudent;

import java.util.List;

public interface StudentRepository {
    List<Student> findAll();
}
