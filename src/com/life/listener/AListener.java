package com.life.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class AListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Map<String,Integer> map = new LinkedHashMap<>();
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("map",map);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
