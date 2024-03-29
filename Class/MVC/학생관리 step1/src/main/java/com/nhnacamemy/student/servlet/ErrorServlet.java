package com.nhnacamemy.student.servlet;

import com.nhnacamemy.student.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.nhnacamemy.student.RequestDispatcher.ERROR_STATUS_CODE;
import static javax.servlet.RequestDispatcher.*;
import static javax.servlet.RequestDispatcher.ERROR_REQUEST_URI;

@WebServlet (name="errorServlet", urlPatterns = "/error")
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
        req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
        req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
        req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
        req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));

        //todo /error.jsp forward 처리
        RequestDispatcher rd = (RequestDispatcher) req.getRequestDispatcher("/error.jsp");
        rd.forward(req, resp);
    }
}
