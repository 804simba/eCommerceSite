package com.ecommerce.controllers;

import com.ecommerce.dao.CategoryDAO;
import com.ecommerce.dao.ICategoryDAO;
import com.ecommerce.dao.IProductDAO;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.services.CategoryService;
import com.ecommerce.services.ProductService;
import com.ecommerce.services.implementations.CategoryServiceImpl;
import com.ecommerce.services.implementations.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditProductController", value = "/edit-product")
@MultipartConfig(maxFileSize = 1073741824)
public class EditProductController extends HttpServlet {
    ProductService productService;
    CategoryService categoryService;

    public EditProductController() {
        IProductDAO productDAO = new ProductDAO();
        ICategoryDAO categoryDAO = new CategoryDAO();
        this.productService = new ProductServiceImpl(productDAO);
        this.categoryService = new CategoryServiceImpl(categoryDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get request for product in DB
        int productID = Integer.parseInt(request.getParameter("productID"));
        // get product from database
        Product product = productService.getProductByID(productID);
        // get Category for product
        List<Category> categories = categoryService.getAllCategories();
        List<String> images = productService.getAllProductImages();
        request.setAttribute("product", product);
        request.setAttribute("categories", categories);
        request.setAttribute("images", images);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-product.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the product ID from request
        int productID = Integer.parseInt(request.getParameter("product-id"));
        // Get product information from request
        String productName = request.getParameter("product-name");
        String imageName = request.getParameter("product-image");
        String description = request.getParameter("product-description");
        String productPrice = request.getParameter("product-price");
        String quantity = request.getParameter("product-quantity");

        productService.editProduct(productID, productName, imageName, productPrice, description, quantity);
        response.sendRedirect("product-management");
    }
}
