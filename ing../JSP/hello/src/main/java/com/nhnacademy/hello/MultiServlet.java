package com.nhnacademy.hello;

import org.jsoup.Jsoup;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class MultiServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(MultiServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String[] values = req.getParameterValues("class");

        try (PrintWriter out = resp.getWriter()) {
            out.println(String.join(", ", values));
        } catch (IOException e) {
            log.info("MultiServlet Error: " + e.getMessage());
        }

//        try (PrintWriter out = resp.getWriter()) {
//            String[] values = req.getParameterValues("class");
//            for (int i = 0; i < values.length; i++) {
//                out.print(values[i]);
//            }
//        } catch (IOException e) {
//            log.info("MultiServlet Error: " + e.getMessage());
//        }
    }
}
