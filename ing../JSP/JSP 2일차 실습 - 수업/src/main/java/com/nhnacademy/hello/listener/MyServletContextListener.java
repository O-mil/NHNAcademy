package com.nhnacademy.hello.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
public class MyServletContextListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        javax.servlet.ServletContextListener.super.contextInitialized(sce);

        ServletContext servletContext = sce.getServletContext();
        String counterFileName = servletContext.getInitParameter("counterFileName");

        String counterFilePath = "/WEB-INF/classes/" + counterFileName;
        String realFilePath = servletContext.getRealPath(counterFilePath);
        log.error("realCounterFilePath: {}", realFilePath);

        File target = new File(realFilePath);

        if (target.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(target);
                 InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                 BufferedReader br = new BufferedReader(inputStreamReader);
                 ) {
                long c = Long.parseLong(br.readLine());
                servletContext.setAttribute("counter", c);      // setAttribute를 하면 전역에서 "counter"를 쓸 수 있다.

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.error("init counter: {}", servletContext.getAttribute("counter"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        javax.servlet.ServletContextListener.super.contextDestroyed(sce);

        ServletContext servletContext = sce.getServletContext();
        String counterFileName = servletContext.getInitParameter("counterFileName");
        String counterFilePath = "/WEB-INF/classes/" + counterFileName;
        String realFilePath = servletContext.getRealPath(counterFilePath);

        try (FileOutputStream fileOutputStream = new FileOutputStream(realFilePath);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
             BufferedWriter bw = new BufferedWriter(outputStreamWriter);
             ) {
            bw.write(String.valueOf(servletContext.getAttribute("counter")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("distory-counter: " + servletContext.getAttribute("counter"));
    }
}
