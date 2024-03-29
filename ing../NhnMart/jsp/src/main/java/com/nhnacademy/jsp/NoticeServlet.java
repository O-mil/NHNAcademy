package com.nhnacademy.jsp;

import org.apache.commons.math3.random.RandomDataGenerator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (name = "noticeServlet", urlPatterns = "/notice")
public class NoticeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Notice> noticeList = new ArrayList<>();
        RandomDataGenerator generator = new RandomDataGenerator();
        for (int i = 0; i < 10; i++) {
            String subject = "공지사항" + i;
            String name = "nhn아카데미" + i;
            long counter = new RandomDataGenerator().nextLong(100000, 90000000);
            noticeList.add(new Notice(subject, name, counter));
        }
        req.setAttribute("noticeList", noticeList);

        RequestDispatcher rd = req.getRequestDispatcher("notice.jsp");
        rd.forward(req, resp);
    }
}
