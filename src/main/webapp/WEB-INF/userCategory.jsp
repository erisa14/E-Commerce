<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Category</title>
    <link rel="stylesheet" href="/css/stylee.css">
    <!-- for Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
    <!-- For any Bootstrap that uses JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <!-- CSS for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

    <!-- MY own CSS -->
    <style>
        .my-span {
            font-size: 0.5rem;
        }
    </style>

</head>
<body class="font" >
<!-- NAV BAR -->
<nav class="navbar navbar-expand-lg navbar-light navbar-style">
    <div class="container-fluid">
        <ul class="navbar-nav d-flex flex-row me-1">
            <a class="navbar-brand" href="/users/home">Dashboard</a>
            <a class="navbar-brand dropdown-toggle" data-bs-toggle="dropdown" role="button" aria-expanded="false">Category</a>
            <ul class="dropdown-menu navbar-style">
                <c:forEach items="${categories}" var="category">
                    <li><a class="dropdown-item" href="/users/${category.name}">${category.name}s</a></li>
                </c:forEach>
            </ul>
        </ul>


        <ul class="navbar-nav d-flex flex-row me-1" >
            <li class="me-3" style="position: relative;">
                <a class="navbar-brand" href="/viewWishlist">
                    <span class="my-span start-100 translate-middle badge rounded-pill bg-danger">${numberOfWishlistItems}</span>
                    <i class="my-icon fas fa-heart"></i>
                </a>
            </li>
            <li class="me-3" style="position: relative;">
                <a class="navbar-brand" href="/viewCart">
                    <span class="my-span start-100 translate-middle badge rounded-pill bg-danger">${numberOfCartItems}</span>
                    <i class="my-icon fas fa-shopping-cart"></i>
                </a>
            </li>
            <li>
                <a class="navbar-brand" href="/logout">Log out</a>
            </li>
        </ul>
    </div>
</nav>


<!-- HEADER -->
<div id="intro-example" class="p-5 text-center bg-image" style="background-image: url('/assets/${categoryName.categoryImage}');">
    <div class="mask" style="background-color: rgba(0, 0, 0, 0.2);">
        <div class="d-flex justify-content-center align-items-center">
            <div class="text-white">
                <h1 class="mb-3">${categoryName.name}s</h1>
            </div>
        </div>
    </div>
</div>



<div class="my-container">

    <!-- PRODUCTS -->
    <div class="row">
        <c:forEach items="${categories}" var="category">
            <c:forEach items="${products}" var="product">
                <c:if test="${category.name eq product.category.name}">
                    <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
                        <div class="view-product">
                            <a class="ripple nav-link" href="/users/view/${product.id}">
                                <img src="/assets/${product.image}" class="view-img img-fluid rounded" alt="${product.productName}"/>
                            </a>
                            <div class="d-flex">
                                <div class="col">
                                    <a class="justify-center" href="/users/view/${product.id}">${product.productName}</a>
                                    <p class="justify-center">$${product.price}</p>
                                </div>
                                <div class="col">
                                    <form action="/new_wishlist_item" method="post">
                                        <input type="hidden" name="productId" value="${product.id}">
                                        <button type="submit" class="btn">
                                            <i class="fas fa-heart" style="color: #1254a1;"></i>
                                        </button>
                                    </form>                                    <form action="/new_cart_item" method="post">
                                        <input type="hidden" name="productId" value="${product.id}">
                                        <button type="submit" class="btn">
                                            <i class="fas fa-shopping-cart" style="color: #1254a1;"></i>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </c:forEach>
    </div>

</div>
</body>
</html>