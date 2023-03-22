<%--
  Created by IntelliJ IDEA.
  User: decagon
  Date: 21/03/2023
  Time: 05:59
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>--%>
<html>
<jsp:include page="./assets/templates/head.jsp"/>
<body class="config" id="js-scrollspy-trigger">
<%--<jsp:include page="./assets/templates/header.jsp"/>--%>
<div class="bg-light py-3">
    <div class="container">
        <div class="row">
            <div class="col-md-12 mb-0"><a href="/">Home</a> <span class="mx-2 mb-0">/</span> <strong
                    class="text-black">Products management</strong></div>
        </div>
    </div>
</div>

<div class="site-section">
    <div class="container">

        <div class="row mb-5">
            <div class="col-md-12">
                <div class="site-blocks-table">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Image</th>
                            <th>ID</th>
                            <th style="max-width: 120px">Product name</th>
                            <th>Price</th>
                            <th>Category</th>
                            <th>Total quantity</th>
                            <th>Created At</th>
                            <th>Modified At</th>
                            <th style="min-width: 195px">Edit / Remove</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${empty products}">
                                <tr>
                                    <td colspan="9">No products found</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${products}" var="o" varStatus="loop">
                                    <tr>
                                        <td class="product-thumbnail">
                                            <img src="/Users/decagon/IdeaProjects/ECommerce/src/main/webapp/assets/images/product/${images[loop.index]}" alt="Product Image" class="img-fluid">
                                        </td>
                                        <td>${o.productID}</td>
                                        <td>${o.productName}</td>
                                        <td>$${o.productPrice}</td>
                                        <td>${o.category.name}</td>
                                        <td>${o.quantity}</td>
                                        <td>${o.createdAt}</td>
                                        <td>${o.modifiedAt}</td>
                                        <td>
                                            <a href="edit-product?productID=${o.productID}" class="btn btn-primary btn-sm"
                                               style="background-color: green ; border-color: green">
                                                <span class="icon icon-pencil"></span>
                                            </a>
                                            <a href="remove-product?productID=${o.productID}"
<%--                                               class="btn btn-primary btn-sm ${(o.isDeleted) ? "disabled" : " "}"--%>
                                               style="background-color: red ; border-color: red">
                                                <span class="icon icon-trash"></span>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6">
                <div class="row mb-5">
                    <div class="col-md-6">
                        <button class="btn btn-outline-primary btn-sm btn-block">Delete</button>
                    </div>

                    <!-- Button trigger add product modal -->
                    <div class="col-md-6 mb-3 mb-md-0">
                        <button class="btn btn-primary btn-sm btn-block" data-toggle="modal"
                                data-target="#addProductModal">Add product
                        </button>
                    </div>

                    <!-- Add product Modal -->
                    <div class="modal fade bd-example-modal-lg" id="addProductModal" tabindex="-1" role="dialog"
                         aria-labelledby="myLargeModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg modal-dialog-centered">
                            <form class="modal-content" action="add-product" method="post"
                                  enctype="multipart/form-data">
                                <div class="modal-header">
                                    <h5 class="modal-title text-black" id="addProductModalLabel">
                                        Product information
                                    </h5>

                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>

                                <div class="modal-body" style="padding: 0">
                                    <div class="p-3">
                                        <div class="form-group row">
                                            <div class="col-md-12">
                                                <label for="product-name" class="text-black">
                                                    Name <span class="text-danger">*</span>
                                                </label>

                                                <input name="product-name" type="text" class="form-control"
                                                       id="product-name">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <div class="col-md-12">
                                                <label for="product-image" class="text-black">
                                                    Image <span class="text-danger">*</span>
                                                </label>

                                                <input name="product-image" type="file" class="form-control"
                                                       id="product-image">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <div class="col-md-12">
                                                <label for="product-price" class="text-black">
                                                    Price <span class="text-danger">*</span>
                                                </label>

                                                <input name="product-price" type="number" class="form-control"
                                                       id="product-price">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <div class="col-md-12">
                                                <label for="product-description" class="text-black">
                                                    Description <span class="text-danger">*</span>
                                                </label>

                                                <textarea name="product-description" id="product-description" cols="30"
                                                          rows="7" class="form-control"></textarea>
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <div class="col-md-12">
                                                <label for="product-quantity" class="text-black">
                                                    Quantity <span class="text-danger">*</span>
                                                </label>

                                                <input name="product-quantity" type="number" class="form-control"
                                                       id="product-quantity">
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <div class="col-md-12">
                                                <label for="product-category" class="text-black">
                                                    Category <span class="text-danger">*</span>
                                                </label>

                                                <select name="product-category" id="product-category"
                                                        class="form-control">
                                                        <option value="1">MEN</option>
                                                        <option value="2">WOMEN</option>
                                                        <option value="3">KIDS</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-outline-primary btn-sm btn-block"
                                            data-dismiss="modal" style="margin-top: 0">
                                        Cancel
                                    </button>

                                    <button type="submit" class="btn btn-primary btn-sm btn-block"
                                            style="margin-top: 0">
                                        Save
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="./assets/templates/scripts.jsp"/>
</body>
</html>
