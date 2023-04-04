package com.nhnacademy.hello.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class loginCheckFilter implements Filter {

    private final Set<String> excludeUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        String urls = filterConfig.getInitParameter("exclude-urls");
        log.error("exclude-urls: {}", urls);
        Arrays.stream(urls.split("\n"))     //System.lineSeparator(): 라인 자르는 기능. "\n"과 같음)
                .map(String::trim)
                .forEach(excludeUrls::add);

//        String[] arr = urls.split(System.lineSeparator());
//        for (String s : arr) {
//            excludeUrls.add(s);
//        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // requestUri: '/login', '/login.html' 등
        String requestUri = ((HttpServletRequest) servletRequest).getRequestURI();

        // excludeUrls에 포함되지 않으면
        if (!excludeUrls.contains(requestUri)) {
            HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);  // true는 없으면 생성을 해버리므로 false로 처리해 준다.

            // 세션이 없으면 "/login.html"로 이동 (Redirect)
            if (Objects.isNull(session)) {
                ((HttpServletResponse) servletResponse).sendRedirect("/login.html");
            }
        }
        //세션이 있으면 다음 필터 걸어주기
        filterChain.doFilter(servletRequest, servletResponse);
        // doFilter를 servlet 앞에 붙이면 servlet이 실행되기 전에 필터가 먼저 실행된다. -> 구조 기억해두기️⭐️
    }
}
