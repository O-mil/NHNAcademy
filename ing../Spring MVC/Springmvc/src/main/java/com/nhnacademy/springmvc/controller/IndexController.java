package com.nhnacademy.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index() {
        //WEB-INF/views/index.jsp -> 앞 뒤 때고 index만 리턴해주면 됨.
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public String postIndex() {
        return "";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/")
    public String deleteIndex() {
        return "";
    }
}
