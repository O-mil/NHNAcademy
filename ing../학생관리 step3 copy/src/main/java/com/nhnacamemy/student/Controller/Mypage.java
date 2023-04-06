package com.nhnacamemy.student.Controller;

import com.nhnacamemy.student.init.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/mypage.do", method = RequestMapping.Method.GET)
public class Mypage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/mypage.jsp";
    }
}
