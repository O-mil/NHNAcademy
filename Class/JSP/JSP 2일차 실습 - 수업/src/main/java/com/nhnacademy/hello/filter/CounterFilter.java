package com.nhnacademy.hello.filter;

import com.nhnacademy.hello.util.CounterUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(
        filterName = "countFilter",
        urlPatterns = "/*"
        )
@Slf4j
public class CounterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        CounterUtils.increaseCounter(servletRequest.getServletContext());
        filterChain.doFilter(servletRequest, servletResponse);
        log.error("counter: {}", servletRequest.getServletContext().getAttribute("counter"));
    }
}
