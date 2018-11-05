package com.life.servlet;


import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AServlet")
public class AServlet extends BaseServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

    }

    public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        System.out.println("addUser");
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        System.out.println("deleteUser");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("激活了");
    }
}
