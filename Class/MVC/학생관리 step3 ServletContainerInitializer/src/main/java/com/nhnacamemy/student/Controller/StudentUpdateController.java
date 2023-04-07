package com.nhnacamemy.student.Controller;

import com.nhnacamemy.student.Gender;
import com.nhnacamemy.student.Student;
import com.nhnacamemy.student.init.RequestMapping;
import com.nhnacamemy.student.repository.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RequestMapping (value = "/student/update.do", method = RequestMapping.Method.POST)
public class StudentUpdateController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");

        String id = request.getParameter("id");
        String name = request.getParameter("name");

        Gender gender = null;
        if (Objects.nonNull(request.getParameter("gender"))) {
            gender = Gender.valueOf(request.getParameter("gender"));
        }

        Integer age = null;
        if (Objects.nonNull(request.getParameter("age"))) {
            age = Integer.parseInt(request.getParameter("age"));
        }

        if (Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(gender) || Objects.isNull(age)) {
            throw new RuntimeException("아이디, 이름, 성별, 나이 확인 바람");
        }

        Student student = new Student(id, name, gender, age);
        studentRepository.update(student);

        return "redirect:/student/view.do?id=" + student.getId();
    }
}
