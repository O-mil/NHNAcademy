package com.nhnacamemy.student.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.nhnacamemy.student.RequestDispatcher.ERROR_STATUS_CODE;

@WebServlet (name="errorServlet", urlPatterns = "/error")
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));

        //todo exception_type
        //todo message
        //todo exception
        //todo request_uri

        //todo /error.jsp forward 처리
    }
}
