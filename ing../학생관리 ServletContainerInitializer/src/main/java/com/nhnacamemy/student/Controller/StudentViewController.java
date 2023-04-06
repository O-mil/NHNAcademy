package com.nhnacamemy.student.Controller;

import com.nhnacamemy.student.exception.StudentNotFoundException;
import com.nhnacamemy.student.Student;
import com.nhnacamemy.student.init.RequestMapping;
import com.nhnacamemy.student.repository.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RequestMapping(value = "/student/view.do", method = RequestMapping.Method.GET)
public class StudentViewController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        StudentRepository studentRepository = (StudentRepository)  request.getServletContext().getAttribute("studentRepository");

        String id = request.getParameter("id");
        if (Objects.isNull(id)) {
            throw new RuntimeException("parameter [id]: null");
        }

        Student student = studentRepository.getStudentById(id);
        if (Objects.isNull(student)) {
            throw new StudentNotFoundException(id);
        }
        request.setAttribute("student", student);

        return "/student/view.jsp";
    }
}
