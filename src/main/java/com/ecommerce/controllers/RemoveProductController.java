package com.ecommerce.controllers;

import com.ecommerce.dao.ProductDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveProductController", value = "/remove-product")
public class RemoveProductController extends HttpServlet {
    ProductDAO productDAO;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the ID of the product from the request
        int productID = Integer.parseInt(request.getParameter("product-id"));
        // Remove product from DB
        productDAO.deleteProduct(productID);
        response.sendRedirect("product-management");
    }
}
