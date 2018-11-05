package com.life.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        /*
        处理POST请求
         */
        servletRequest.setCharacterEncoding("UTF-8");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        /*
        处理GET请求
            1.掉包request
         */
        if(httpServletRequest.getMethod().equals("POST")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else if(httpServletRequest.getMethod().equals("GET")){
            EncodingRequest encodingRequest = new EncodingRequest(httpServletRequest);
            filterChain.doFilter(encodingRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
