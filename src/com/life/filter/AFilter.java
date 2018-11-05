package com.life.filter;

import javax.servlet.*;
import java.io.IOException;

public class AFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器生成");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截");
        filterChain.doFilter(servletRequest,servletResponse);//放行
    }

    @Override
    public void destroy() {

    }
}
