package com.example.casestudy3.controller;

import com.example.casestudy3.model.User;
import com.example.casestudy3.service.user.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init()  {
        userService = new UserService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action ="";
        }
        switch (action){
            case "detailUser": detailUser(request,response); break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action ="";
        }
        switch (action){
            case "createPlayList": createPlayList(request,response); break;
            case "buySong": userBuySong(request,response); break;
        }
    }
    private void createPlayList(HttpServletRequest request,HttpServletResponse response) throws IOException {
        userService.createPlayList(request);
        response.sendRedirect("/UserServlet?action=detailUser");
    }
    private void detailUser(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/detail_user/detail_user.jsp");
        request.setAttribute("user",userService.detailUser(request));
        requestDispatcher.forward(request, response);
    }
    private void userBuySong(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        userService.buySong(request);
        response.sendRedirect("homeUser");
    }
}
