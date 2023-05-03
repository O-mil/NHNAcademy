package com.nhnacademy.hello;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Slf4j
public class LoginServlet extends HttpServlet {

    private String userId;
    private String userPassword;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userId = config.getInitParameter("id");      // 이걸로 web.xml에 있는 파라미터 가져 옴
        userPassword = config.getInitParameter("pwd");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //있으면 가져오고 없으면 null
        HttpSession session = req.getSession(false);

        if (Objects.isNull(session)) {
            resp.sendRedirect("/login.html");
        } else {
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset='utf-8'>");
                out.println("</head>");
                out.println("<body>");
                out.println("login success : id =" + session.getAttribute("id") + "<br/>");
                out.println("Seesion id: " + session.getId());

                out.println("<form method='post' action='/logout'>");
                out.println("<button type='submit>로그아웃</button>");
                out.println("</form>");
//                out.println("<a href='/logout'>logout</a>");
                out.println("</body>");
                out.println("</html>");
            } catch (IOException e) {
                log.error("login: {}", e.getMessage(), e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("userId");
        String pwd = req.getParameter("userPassword");

        if (userId.equals(id) && userPassword.equals(pwd)) {
            //있으면 가져오고 없으면 생성
            HttpSession session = req.getSession();
            session.setAttribute("id", id);
            resp.sendRedirect("/login");
        } else {
            log.error("아이디/패스워드가 일치하지 않습니다.");
            resp.sendRedirect("/login.html");
        }
    }
}
