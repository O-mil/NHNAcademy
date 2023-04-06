package com.nhnacamemy.student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.nhnacamemy.student.RequestDispatcher.ERROR_STATUS_CODE;
import static javax.servlet.RequestDispatcher.*;
import static javax.servlet.RequestDispatcher.ERROR_REQUEST_URI;

public class ErrorController implements Command {
    @Override
    public String excute(HttpServletRequest req, HttpServletResponse resp) {

        req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
        req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
        req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
        req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
        req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));

        return "/error.jsp";


    }
}
