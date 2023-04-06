package com.nhnacamemy.student.servlet;



import com.nhnacamemy.student.Student;
import com.nhnacamemy.student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Slf4j
@WebServlet (name = "studentListServlet", urlPatterns = "/student/list")

public class StudentListServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. html, encoding 처리
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        //student list 구하기
        List<Student> studentList = studentRepository.getStudents();
        req.setAttribute("studentList", studentList);

//        // /student/list.jsp -> forward 하기
//        RequestDispatcher rd = req.getRequestDispatcher("/student/list.jsp");
//        rd.forward(req, resp);

        //todo view attribute - /student/list.jsp
        req.setAttribute("view", "/student/list.jsp");
    }
}
