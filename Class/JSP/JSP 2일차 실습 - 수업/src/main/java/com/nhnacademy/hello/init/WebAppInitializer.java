package com.nhnacademy.hello.init;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

public class WebAppInitializer implements ServletContainerInitializer {


    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("url", "https://nhnacademy.com");
        servletContext.setInitParameter("counterFileName", "counter.dat");
    }
}
