package com.life.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet("/BaseServlet")
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = req.getParameter("method");
        if(methodName==null || methodName.trim().isEmpty()){
            throw new RuntimeException("missing method parameter");
        }
        Class c = this.getClass();
        Method method = null;
        try {
            method = c.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException("method error");
        }
        try {
            method.invoke(this,req,resp);
        } catch (Exception e) {
            throw new RuntimeException("调用方法内部发生错误");
        }
    }
}
