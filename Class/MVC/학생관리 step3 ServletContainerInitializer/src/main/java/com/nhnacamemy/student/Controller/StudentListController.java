package com.nhnacamemy.student.Controller;

import com.nhnacamemy.student.Student;
import com.nhnacamemy.student.init.RequestMapping;
import com.nhnacamemy.student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RequestMapping(value = "/student/list.do", method = RequestMapping.Method.GET)
public class StudentListController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");

        List<Student> studentList = studentRepository.getStudents();
        request.setAttribute("studentList", studentList);

        return "/student/list.jsp";
    }
}
