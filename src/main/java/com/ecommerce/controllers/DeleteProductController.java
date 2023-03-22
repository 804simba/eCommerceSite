package com.ecommerce.controllers;

import com.ecommerce.dao.ProductDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteProductController", value = "/delete-product")
public class DeleteProductController extends HttpServlet {
    ProductDAO productDAO = new ProductDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the ID of the product from the request
        int productID = Integer.parseInt(request.getParameter("productID"));
        // Remove product from DB
        productDAO.deleteProduct(productID);
        response.sendRedirect("product-management");
    }
}
