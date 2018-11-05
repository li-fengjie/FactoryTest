package com.life.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异步处理
 */
@WebServlet(urlPatterns = "/CServlet",asyncSupported = true)
public class CServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type","text/html;charset=utf-8");

//        ie 输出太小时加载完才刷新，无异步效果
//        for(int j = 0; j < 512; j ++){
//            response.getWriter().print("a");
//            response.getWriter().flush();
//        }

        //1、得到异步上下文对象
        AsyncContext asyncContext = request.startAsync(request,response);
        //2、给上下文对象一个Runnable对象

        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    response.getWriter().print("异步处理</br>");
                    response.getWriter().flush();
                    Thread.sleep(200);
                    for(int i = 0; i < 50; i ++){
                        response.getWriter().print(i + "");
                        response.getWriter().flush();
                        Thread.sleep(250);
                    }
                    //通知Tomcat 线程执行结束
                    asyncContext.complete();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
