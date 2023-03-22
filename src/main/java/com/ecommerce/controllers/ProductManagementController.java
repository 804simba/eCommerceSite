package com.ecommerce.controllers;

import com.ecommerce.dao.CategoryDAO;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;

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
        // get the adminID from session
//        HttpSession session = request.getSession();
////        User user = new User();
        List<Product> products = productDAO.getAllProducts();
        List<String> images = productDAO.getAllProductImages();
        System.out.println("Products size: " + products.size());
        System.out.println("images size: " + images.size());

        request.setAttribute("products", products);
        request.setAttribute("images", images);
        // set attribute active status for product management tab in header
//        request.setAttribute("product-management-active", "active");

        // Get request dispatcher and render to product-management page.
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-management.jsp");
        dispatcher.forward(request, response);
    }
}
