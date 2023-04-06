package com.nhnacamemy.student.controller;

import com.nhnacamemy.student.init.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RequestMapping (value="/mypage.do", method = RequestMapping.Method.GET)
public class MyPage implements Command {

    @Override
    public String excute(HttpServletRequest req, HttpServletResponse resp) {
        return "/mypage.jsp";
    }
}
