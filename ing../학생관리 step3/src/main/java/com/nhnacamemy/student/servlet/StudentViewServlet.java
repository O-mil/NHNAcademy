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
import java.util.Objects;

@Slf4j
@WebServlet (name="studentViewServlet", urlPatterns = "/student/view")
public class StudentViewServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        //todo id null check
        String id = req.getParameter("id");
        if (Objects.isNull(id)) {
            throw new RuntimeException("parameter [id]: null");
        }

        Student student = studentRepository.getStudentById(id);
        if (Objects.isNull(student)) {
            throw new RuntimeException("Student Not Found: " + id);
        }
        log.error("student: {}", student);
        //todo student 조회
        req.setAttribute("student", student);

//        //todo /student/view.jsp -> forward
//        RequestDispatcher rd = req.getRequestDispatcher("/student/view.jsp");
//        rd.forward(req, resp);

        //todo view attribute 설정 - /student/view.jsp
        req.setAttribute("view", "/student/view.jsp");
    }
}
