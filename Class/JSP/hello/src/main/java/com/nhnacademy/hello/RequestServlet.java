package com.nhnacademy.hello;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Slf4j
public class RequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");       //페이지 형식을 html로 보여줌 ("text/plain"일 경우 코드를 텍스트 그대로 보여줌)
        resp.setCharacterEncoding("UTF-8");
        resp.setBufferSize(1024);

        String userId = req.getParameter("userId");
        if (Objects.isNull(userId) || userId.isEmpty()) {
            resp.sendError(500, "userId is empty!");        // error 발생시키기
            return;
        }

        String redirect = req.getParameter("redirect");
        if (Objects.nonNull(redirect)) {
            resp.sendRedirect(redirect);        // redirect가 있으면 그 url로 이동!!
        }

        try (PrintWriter out = resp.getWriter()) {
            out.println("locale=" + req.getLocale());
            out.println("parameter name=" + req.getParameter("userId"));
            //out.flush();
            //out.close();      //여기서 종료하면 밑에 있는 애들은 출력 안 됨

            out.println("content type=" + req.getContentType());
            out.println("content length=" + req.getContentLengthLong());
            out.println("method=" + req.getMethod());
            //resp.reset();             //위쪽 애들 리셋되서 출력 안 됨
            out.println("servlet path=" + req.getServletPath());
            out.println("request uri=" + req.getRequestURI());
            out.println("request url=" + req.getRequestURL());
            out.println("User-Agent header=" + req.getHeader("User-Agent"));

            //resp.setStatus(500);
            //resp.sendError(500, "userId is empty!");        // error 발생시키기
        } catch (IOException e) {
            log.error("requestServlet: {}", e.getMessage(), e);
        }
    }
}
