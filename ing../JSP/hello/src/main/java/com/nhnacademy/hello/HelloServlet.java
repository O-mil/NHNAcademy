package com.nhnacademy.hello;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Date 03/04/2023
 */
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        try(PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
                out.println("<head>");
                    //out.print("<meta charset='UTF-8 />");     // 이거 쓰면 글자 출력 안 됨.
                    out.println("<title>hello servlet</title>");
                out.println("</head>");
                out.println("<body>");
                    out.println("<p>hello servlet!!</p>");
                    out.println("<p>안녕 서블릿!!</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
