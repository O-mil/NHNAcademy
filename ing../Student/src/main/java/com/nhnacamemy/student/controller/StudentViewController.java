package com.nhnacamemy.student.controller;

import com.nhnacamemy.student.Student;
import com.nhnacamemy.student.exception.StudentNotFoundException;
import com.nhnacamemy.student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class StudentViewController implements Command {

    @Override
    public String excute(HttpServletRequest req, HttpServletResponse resp) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");
        if(Objects.isNull(id)){
            throw new RuntimeException("parameter [id] : null ");
        }

        Student student = studentRepository.getStudentById(id);
        if(Objects.isNull(student)){
            throw new StudentNotFoundException(id);
        }
        req.setAttribute("student",student);

        return "/student/view.jsp";
    }
}
