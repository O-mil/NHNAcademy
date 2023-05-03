package com.nhnacademy.hello;

import com.nhnacademy.hello.util.CounterUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @Date 03/04/2023
 */
public class HelloServlet extends HttpServlet {

    public static final Logger log = Logger.getLogger(com.nhnacademy.hello.HelloServlet.class.getName());
    // ~class에서 ~log가 찍혔다고 알려줌.

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");     // 한글 깨짐 방지

        String title = getServletConfig().getInitParameter("title");
        String name = getServletConfig().getInitParameter("name");
        String url = getServletContext().getInitParameter("url");
        CounterUtils.increaseCounter(getServletContext());
        long counter = (long) getServletContext().getAttribute("counter");
        Cookie cookie = new Cookie("userName", "omil");     //쿠키 저장
        resp.addCookie(cookie);

        if (Objects.isNull(title)) {
            title = "Mr.";
        }

        if (Objects.isNull(name)) {
            name = "omil";
        }

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
                    out.println("<p>hello! " + title + " " + name + "</p>");
                    out.println("<p>url: " + url + "</p>");
                    out.println("<p>counter: " + counter + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    //servlet 생성 후 초기화 작업을 위해 호출
    @Override
    public void init(ServletConfig config) throws ServletException {
        log.info("init()");
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("service()");
        super.service(req, resp);
    }


    @Override
    public void destroy() {
        log.info("destory()");
        super.destroy();
    }
}
