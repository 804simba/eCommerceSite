package com.ecommerce.controllers;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.entity.User;
import com.ecommerce.utils.PasswordValidation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "SignUpController", value = "/sign-up")
public class SignUpController extends HttpServlet {
    UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fname = request.getParameter("reg-fname");
        String lname = request.getParameter("reg-lname");
        String email = request.getParameter("reg-email");
        String password = request.getParameter("reg-password");
        User user = new User();
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setEmail(email);
        user.setPassword(password);

        HttpSession session = request.getSession();
        if (!userDAO.registerUser(user)) {
            String errorMessage = "failed";
            session.setAttribute("Registration Status", errorMessage);
        } else {
            System.out.println("Success");
            session.setAttribute("Registration Status", "Successfully registered! Login to continue..");
        }
        response.sendRedirect("sign-in.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("sign-up.jsp");
    }
}
