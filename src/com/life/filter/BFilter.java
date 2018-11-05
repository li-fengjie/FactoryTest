package com.life.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Map;

public class BFilter implements Filter{
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        Map<String,Integer> map = (Map<String, Integer>) servletContext.getAttribute("map");
        String ip = servletRequest.getRemoteAddr();
        if(map.containsKey(ip)){
            int count = map.get(ip);
            map.put(ip,count+1);
        }else {
            map.put(ip,1);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
