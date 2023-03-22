package com.ecommerce.controllers;

import com.ecommerce.dao.CategoryDAO;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet(name = "EditProductController", value = "/edit-product")
@MultipartConfig(maxFileSize = 1073741824)
public class EditProductController extends HttpServlet {
    ProductDAO productDAO = new ProductDAO();
    CategoryDAO categoryDAO = new CategoryDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get request for product in DB
        int productID = Integer.parseInt(request.getParameter("productID"));
        // get product from database
        Product product = productDAO.getProductById(productID);
        // get Category for product
        List<Category> categories = categoryDAO.getAllCategories();
        List<String> images = productDAO.getAllProductImages();
        request.setAttribute("product", product);
        request.setAttribute("categories", categories);
        request.setAttribute("images", images);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-product.jsp");
        dispatcher.forward(request, response);
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Get the product ID from request
//        int productID = Integer.parseInt(request.getParameter("productID"));
//        // Get product information from request
//        String productName = request.getParameter("product-name");
//        String productPrice = request.getParameter("product-price");
//        String description = request.getParameter("product-description");
//        int quantity = Integer.parseInt(request.getParameter(""));
//        int category = Integer.parseInt(request.getParameter("product-category"));
//
//        Part part = request.getPart("product-image");
//        InputStream inputStream = part.getInputStream();
//
//        productDAO.editProduct(productID, productName, inputStream, productPrice, description, category, quantity);
//        response.sendRedirect("product-management");
//    }
}
