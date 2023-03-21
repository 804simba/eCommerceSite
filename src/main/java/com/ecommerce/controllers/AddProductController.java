package com.ecommerce.controllers;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "AddProductController", value = "/add-product")
public class AddProductController extends HttpServlet {
    ProductDAO productDAO = new ProductDAO();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get product information from request
        String productName = request.getParameter("product-name");
        double productPrice = Double.parseDouble(request.getParameter("product-price"));
        String description = request.getParameter("product-description");
        int quantity = Integer.parseInt(request.getParameter("product-quantity"));
        int category = Integer.parseInt(request.getParameter("product-category"));

        // Get uploaded image
        Part part = request.getPart("product-image");
        InputStream inputStream = part.getInputStream();

        // Get admin id for product
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("admin");
        int userID = user.getUserID();

        // add product to DB
        productDAO.addProduct(productName, productPrice, description, category, quantity, inputStream);
        response.sendRedirect("product-management");
    }
}
