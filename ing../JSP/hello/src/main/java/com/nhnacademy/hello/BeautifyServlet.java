package com.nhnacademy.hello;

import org.jsoup.Jsoup;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class BeautifyServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(BeautifyServlet.class.getName());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");      // html로 보내지 않고 plain(text 그 자체)로 넘김
        resp.setCharacterEncoding("UTF-8");     //get, post 방식 모두 사용 가능 (서블릿에서 직접 브라우저에 출력해줄 때 사용)
        req.setCharacterEncoding("UTF-8");      // post 방식만 사용 가능. (서블릿에서 post방식으로 정보를 서버에 전달해줄 때)
        String html = req.getParameter("html");

        try(PrintWriter out = resp.getWriter()) {
            out.println(Jsoup.parse(html));
        } catch (IOException e) {
            log.info("beautifyServlet Error: " + e.getMessage());
        }
    }
}
