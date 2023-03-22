package com.ecommerce.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HomeController", value = "/index")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null
                && session.getAttribute("email") != null
        && session.getAttribute("password") != null) {
            // Customer is logged in, redirect to home page
            response.sendRedirect("index.jsp");
        } else {
            // Customer is not logged in, show login page
            request.getRequestDispatcher("sign-in.jsp").forward(request, response);
        }
    }
}
