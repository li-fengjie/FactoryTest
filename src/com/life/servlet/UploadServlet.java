package com.life.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * 文件上传 Server 3.0 支持
 *
 */
@WebServlet(name = "UploadServlet",urlPatterns = "/upload")
@MultipartConfig //支持文件上传的注解
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //可以使用getParameter
        String username = request.getParameter("username");
        System.out.println(username);
        /*
        获取文件表单字段，对应的Part 对象
         */
        Part part = request.getPart("image");
        response.getWriter().println(part.getContentType());
        response.getWriter().println(part.getName());
        response.getWriter().println(part.getSize());
        response.getWriter().println(part.getHeader("Content-Disposition"));
        part.write("D:/1.jpg");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
