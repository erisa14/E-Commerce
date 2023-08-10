<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Wishlist</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
    <!-- For any Bootstrap that uses JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <!-- CSS for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

    <!-- MY own CSS -->
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        .my-container {
            padding: 35px 45px;
        }
        .justify-center {                   /*     center     */
            display: flex;
            align-items: center;
            justify-content: center;
        }

        /*nav bar*/
        .navbar-background {
            background-color: #90caf9;
        }
        .my-nav-item {
            color: #1254a1;
        }

        /* HEADER classes */
        .header {
            font-size: 40px;
            font-weight: bold;
            font-family: sans-serif;
            color: #1254a1;
            margin: 20px 0px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light navbar-background">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation" style="border: transparent solid 1px; color: #1254a1; font-weight: bold">Categories</button>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="/home" style="color: #1254a1">All products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/laptop" style="color: #1254a1">Laptops</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/cellphone" style="color: #1254a1">Cellphones</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/pc" style="color: #1254a1">PCs</a>
                </li>
            </ul>
        </div>

        <a class="navbar-brand" href="/viewWishlist" style="color: #1254a1">
            <i class="fas fa-heart"></i>
        </a>
        <a class="navbar-brand" href="/viewCart" style="color: #1254a1">
            <i class="fas fa-shopping-cart"></i>
        </a>
        <a class="navbar-brand" href="/logout" style="color: #1254a1; font-weight: bold">Log out</a>
    </div>
</nav>
<div class="container">
    <h1>Wishlist</h1>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Product Name</th>
            <th>Price</th>
            <th>Image</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${wishlistItems}" var="wishlistItem">
            <tr>
                <td>${wishlistItem.product.productName}</td>
                <td>$${wishlistItem.product.price}</td>
                <td>
                    <img src="/assets/${wishlistItem.product.image}" alt="${wishlistItem.product.productName}" width="100" height="100">
                </td>
                <td class="d-flex">
                    <form:form action="/wishlist_item/${wishlistItem.id}/delete" method="delete">
                        <button class="btn btn-danger mx-1">Remove</button>
                    </form:form>
                    <form action="/add_wishlist_item_to_cart" method="post">
                        <input type="hidden" name="productIds" value="${wishlistItem.product.id}">
                        <button type="submit" class="btn btn-primary">
                            Add to <i class="fas fa-shopping-cart"></i>
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <form action="/add_wishlist_item_to_cart" method="post">
        <c:forEach items="${wishlistItems}" var="wishlistItem">
            <input type="hidden" name="productIds" value="${wishlistItem.product.id}">
        </c:forEach>
        <button type="submit" class="btn btn-primary">
            Add all products to <i class="fas fa-shopping-cart"></i>
        </button>
    </form>



</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

