package com.ecommerce.controllers;

import com.ecommerce.dao.IProductDAO;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.services.ProductService;
import com.ecommerce.services.implementations.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteProductController", value = "/delete-product")
public class DeleteProductController extends HttpServlet {
    private final ProductService productService;
    public DeleteProductController() {
        IProductDAO productDAO = new ProductDAO();
        this.productService = new ProductServiceImpl(productDAO);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the ID of the product from the request
        int productID = Integer.parseInt(request.getParameter("productID"));
        // Remove product from DB
        productService.deleteProduct(productID);
        response.sendRedirect("product-management");
    }
}
