package com.nhnacamemy.student.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String excute(HttpServletRequest req, HttpServletResponse resp);
}
