package com.ecommerce.controllers;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.entity.Product;
import com.ecommerce.exceptions.ProductNotFoundException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductManagementController", value = "/product-management")
public class ProductManagementController extends HttpServlet {
    ProductDAO productDAO = new ProductDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = null;
        try {
            products = productDAO.getAllProducts();
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
        List<String> images = productDAO.getAllProductImages();
        System.out.println("Products size: " + products.size());
        System.out.println("images size: " + images.size());

        request.setAttribute("products", products);
        request.setAttribute("images", images);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-management.jsp");
        dispatcher.forward(request, response);
    }
}
