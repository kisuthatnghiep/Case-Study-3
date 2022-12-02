package com.example.casestudy3.controller;
import com.example.casestudy3.DAO.RegisterDAO;
import com.example.casestudy3.model.Singer;
import com.example.casestudy3.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private RegisterDAO registerDAO;

    public void init() {
        registerDAO = new RegisterDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createForm(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                create(request,response);
                break;
        }
    }

    private void createForm(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("register/register.jsp");
        requestDispatcher.forward(request,response);
    }
    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String telephone = request.getParameter("telephone");
        if (role.equals("user")) {
            User newUser = new User(name, account, password, email, telephone, 0, 1);
            registerDAO.createUser(newUser);
        } else if (role.equals("singer")) {
            Singer newSinger = new Singer(name, account, password, email, telephone, 0, 1);
            registerDAO.createSinger(newSinger);
        }
        response.sendRedirect("http://localhost:8080/login/login.jsp");
    }
}
