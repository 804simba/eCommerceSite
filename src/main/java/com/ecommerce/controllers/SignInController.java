package com.ecommerce.controllers;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.services.UserService;
import com.ecommerce.services.implementations.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/sign-in")
public class SignInController extends HttpServlet {
//    UserDAO userDAO = new UserDAO();
    HttpSession session;
    UserService userService;
    public SignInController() {
        UserDAO userDAO = new UserDAO();
        this.userService = new UserServiceImpl(userDAO);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (email.equals("myadmin@gmail.com") && password.equals("admin")) {
                session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("password", password);
                response.sendRedirect("product-management.jsp");
            } else if (userService.confirmUserLoginCredentials(email, password)) {
                session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("password", password);
                response.sendRedirect("index.jsp");
            } else {
                System.out.println("Login failed");
                System.out.println("password: " + password);
                String alert = "<div class=\"alert alert-danger wrap-input100\">\n" +
                        "                        <p style=\"font-family: Ubuntu-Bold; font-size: 18px; margin: 0.25em 0; text-align: center\">\n" +
                        "                            Wrong username or password!\n" +
                        "                        </p>\n" +
                        "                    </div>";
                // Set attribute for alert tag in login.jsp page.
                request.setAttribute("alert", alert);
                // Resend to login page.
                request.getRequestDispatcher("sign-in.jsp").forward(request, response);
            }
    }
}
