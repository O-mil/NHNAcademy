package com.nhnacademy.hello;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class CounterServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(CounterServlet.class.getName());
    private long count;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        count = Long.parseLong(config.getInitParameter("count"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        count++;

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
                out.println("<title>CounterServlet</title>");
            out.println("</head>");
            out.println("<body>");
                out.println("<p>" + count + "</p>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}
