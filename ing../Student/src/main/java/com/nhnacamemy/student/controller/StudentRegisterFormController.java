package com.nhnacamemy.student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentRegisterFormController implements Command {
    @Override
    public String excute(HttpServletRequest req, HttpServletResponse resp) {
        return "/student/register.jsp";
    }
}
