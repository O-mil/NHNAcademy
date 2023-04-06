package com.nhnacamemy.student.controller;

import com.nhnacamemy.student.Gender;
import com.nhnacamemy.student.Student;
import com.nhnacamemy.student.repository.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class StudentRegisterController implements Command {
    @Override
    public String excute(HttpServletRequest req, HttpServletResponse resp) {

        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");

        String id = req.getParameter("id");
        String name = req.getParameter("name");

        Gender gender = null;
        if (Objects.nonNull(req.getParameter("gender"))) {
            gender = Gender.valueOf(req.getParameter("gender"));
        }

        Integer age = null;
        if (Objects.nonNull(req.getParameter("age"))) {
            age = Integer.parseInt(req.getParameter("age"));
        }

        if (Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(gender) || Objects.isNull(age)) {
            throw new RuntimeException("아이디, 이름, 성별, 나이를 확인해주세요");
        }

        Student student = new Student(id, name, gender, age);
        studentRepository.save(student);

        return "redirect:/student/view.do?id=" + student.getId();
    }
}
