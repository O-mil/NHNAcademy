package com.nhnacademy.hello.login;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet (name = "loginServlet", urlPatterns = "/login",
    initParams = {
        @WebInitParam(name = "id", value = "admin"),
        @WebInitParam(name = "pwd", value = "1234")
    })

@Slf4j
public class LoginServlet extends HttpServlet {
    private String initParamId;
    private String initParamPwd;
    @Override
    public void init(ServletConfig config) throws ServletException {

        initParamId = config.getInitParameter("id");
        initParamPwd = config.getInitParameter("pwd");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // session이 있으면 가져오고 없으면 null
        HttpSession session = req.getSession(false);
        if(Objects.isNull(session) || Objects.isNull(session.getAttribute("id")) ){
            resp.sendRedirect("/login.html");
        }else{
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");

            try(PrintWriter out = resp.getWriter()){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                    out.println("<head>");
                        out.println("<meta charset='utf-8'>");
                    out.println("</head>");
                    out.println("<body>");
                        out.println("login success : id =" + session.getAttribute("id") + "<br/>");
                        out.println("<a href='/logout'>logout</a>");
                    out.println("</body>");
                out.println("</html>");
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");

        if(initParamId.equals(id) && initParamPwd.equals(pwd)){
            //session 있으면 가져오고 없으면 생성
            HttpSession session = req.getSession();
            session.setAttribute("id",id);
            resp.sendRedirect("/login");
        }else{
            log.error("아이디/패스워드가 일치하지 않습니다.");
            //resp.sendRedirect("/login.html");       // 주소: login.html 로 돌아감 (redirect로 리턴)
            RequestDispatcher rd = req.getRequestDispatcher("/login.html");     //일치하지 않으면 보내버리기    //주소: login
            rd.forward(req, resp);      // forward는 주소가 유지됨(url이 변경되지 않음) login.html에 있는 폼(form)을 가져올 때 request객체가 살아있다. -> 폼으로 전송했던 id, pw가 살아있음
            // 아무것도 입력하지 않은 상태로 새로고침 했을 때 똑같이 "아이디/패스워드가 일치하지 않습니다"라는 코드가 log에 뜸.
            // 장바구니 예제에서 forward를 시켜줬을 경우 오이, 당근이 담겨있으면 새로고침 할 경우 오이, 당근이 2개씩 담김 -> 쓰지말란 소리임.
            log.error("id: {}", id);
        }

    }

}
