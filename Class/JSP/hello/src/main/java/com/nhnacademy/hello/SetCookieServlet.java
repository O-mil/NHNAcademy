package com.nhnacademy.hello;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class SetCookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //?locale=en or locale=ko
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String locale = req.getParameter("locale");

        if (Objects.isNull(locale)|| locale.isEmpty()) {
            locale = "ko";
        }

        Cookie cookie = new Cookie("locale", locale);
        cookie.setMaxAge(-1);       // -1은 닫히면 사라짐
        cookie.setPath("/");
        resp.addCookie(cookie);

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<script>alert('쿠키생성');");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>hello servlet!!</p>");
            out.println("<p>안녕 쿠키!</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
