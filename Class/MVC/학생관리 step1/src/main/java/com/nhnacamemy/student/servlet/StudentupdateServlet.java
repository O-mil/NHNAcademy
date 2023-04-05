package com.nhnacamemy.student.servlet;

import com.nhnacamemy.student.Gender;
import com.nhnacamemy.student.Student;
import com.nhnacamemy.student.repository.StudentRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet (name="studentUpdateServlet", urlPatterns = "/student/update")
public class StudentupdateServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        //todo init studentRepository
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        // todo 학생조회
        String id = req.getParameter("id");
        Student student = studentRepository.getStudentById(id);
        if (Objects.isNull(student)) {
            throw new RuntimeException("Student not found: " + id);
        }
        req.setAttribute("student", student);
        resp.setCharacterEncoding("UTF-8");

        // todo forward: /student/register.jsp
        RequestDispatcher rd = req.getRequestDispatcher("/student/register.jsp");
        rd.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo null check
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
            throw new RuntimeException("아이디, 이름, 성별, 나이 확인 바람");
        }

        //todo student 저장
        Student student = new Student(id, name, gender, age);
        studentRepository.update(student);

        //todo /student/view?id=student1 -> redirect
        resp.sendRedirect("/student/view?id=" + student.getId());
    }
}
