<%--
  Created by IntelliJ IDEA.
  User: decagon
  Date: 21/03/2023
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--<jsp:include page="./assets/templates/head.jsp"/>--%>
<head>
    <title>FitShop</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" type="image/png" href="assets/images/logo.png"/>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700">
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">

    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="./assets/css/magnific-popup.css">
    <link rel="stylesheet" href="./assets/css/jquery-ui.css">
    <link rel="stylesheet" href="./assets/css/owl.carousel.min.css">
    <link rel="stylesheet" href="./assets/css/owl.theme.default.min.css">
</head>
<body>
<div class="site-wrap">
<%--    <jsp:include page="./assets/templates/header.jsp"/>--%>

    <div class="bg-light py-3">
        <div class="container">
            <div class="row">
                <div class="col-md-12 mb-0"><a href="/">Home</a> <span class="mx-2 mb-0">/</span> <strong
                        class="text-black">Edit product</strong></div>
            </div>
        </div>
    </div>

    <div class="site-section">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2 class="h3 mb-3 text-black">Product information</h2>
                </div>

                <div class="col-md-7">
                    <form action="edit-product" method="post"
                          enctype="multipart/form-data">
                        <div class="p-3 border">
                            <div class="form-group row">
                                <div class="col-md-12">
                                    <label for="id" class="text-black">
                                        Product ID <span class="text-danger">*</span>
                                    </label>

                                    <input name="product-id" type="text" class="form-control"
                                           id="id" value="${product.id}" readonly>
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-md-12">
                                    <label for="name" class="text-black">
                                        Name <span class="text-danger">*</span>
                                    </label>

                                    <input name="product-name" type="text" class="form-control"
                                           id="name" value="${product.name}">
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-md-12">
                                    <label for="image" class="text-black">
                                        Image <span class="text-danger">*</span>
                                    </label>

                                    <input name="product-image" type="file" class="form-control"
                                           id="image">
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-md-12">
                                    <label for="price" class="text-black">
                                        Price <span class="text-danger">*</span>
                                    </label>

                                    <input name="product-price" type="number" class="form-control"
                                           id="price" value="${product.price}">
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-md-12">
                                    <label for="description" class="text-black">
                                        Description <span class="text-danger">*</span>
                                    </label>

                                    <textarea name="product-description" id="description"
                                              cols="30" rows="7"
                                              class="form-control">${product.description}</textarea>
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-md-12">
                                    <label for="quantity" class="text-black">
                                        Amount <span class="text-danger">*</span>
                                    </label>

                                    <input name="product-quantity" type="number" class="form-control"
                                           id="quantity" value="${product.quantity}">
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-md-12">
                                    <label for="category" class="text-black">
                                        Category <span class="text-danger">*</span>
                                    </label>

                                    <select name="product-category" id="category"
                                            class="form-control">
                                        <c:forEach items="${categories}" var="o">
                                            <option value="${o.id}">${o.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-lg-12">
                                    <input type="submit" class="btn btn-primary btn-lg btn-block" value="Edit product">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="col-md-5 ml-auto">
                    <div class="p-3 border">
                        <img src="data:image/jpg;base64,${product.base64Image}" alt="image" width="100%">
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
