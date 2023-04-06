package com.nhnacamemy.student.Controller;

import com.nhnacamemy.student.Student;
import com.nhnacamemy.student.repository.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class StudentUpdateFormController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");
        String id = request.getParameter("id");
        Student student = studentRepository.getStudentById(id);

        if (Objects.isNull(student)) {
            throw new RuntimeException("Student Not Found: " + id);
        }
        request.setAttribute("student", student);
        return "/student/register.jsp";
    }
}
