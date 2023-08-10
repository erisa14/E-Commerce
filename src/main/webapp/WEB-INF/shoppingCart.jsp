<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart</title>
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
    <h1>Shopping Cart</h1>
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
        <c:forEach items="${cartItems}" var="cartItem">
            <tr>
                <td>${cartItem.product.productName}</td>
                <td>$${cartItem.product.price}</td>
                <td>
                    <img src="/assets/${cartItem.product.image}" alt="${cartItem.product.productName}" width="100" height="100">
                </td>
                <td class="d-flex">
                    <form:form action="/cart_item/${cartItem.id}/delete" method="delete">
                        <button class="btn btn-danger mx-1">Remove</button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>Total Price: ${totalPrice}</p>

    <form action="/shippingDetails" method="post">
        <input type="submit" class="btn btn-primary" value="Next">
    </form>

    <a href="/" class="btn btn-secondary">Cancel</a>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

