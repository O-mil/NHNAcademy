package com.nhnacademy.hello.Filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(this.encoding);
        // do filter로 다음 필터를 실행시켜줌 (마지막에는 꼭! filterChain 걸어주기)
        filterChain.doFilter(servletRequest, servletResponse);
        //response.setCharacterEncoding(encoding);
    }

}
