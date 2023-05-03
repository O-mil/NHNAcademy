package com.nhnacademy.hello.util;


import javax.servlet.ServletContext;

public final class CounterUtils {
    private CounterUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void increaseCounter(ServletContext servletContext) {
        Long count = Long.parseLong(servletContext.getInitParameter("count"));
        Long counter = null;
        if (servletContext.getAttribute("counter") == null) {
            counter = count;
        } else {
            counter = (Long) servletContext.getAttribute("counter");

        }
        counter++;
        servletContext.setAttribute("counter", counter);
    }
}
