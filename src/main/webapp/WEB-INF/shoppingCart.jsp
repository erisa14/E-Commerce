<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="/css/stylee.css">
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
<body class="font">
<nav class="navbar navbar-expand-lg navbar-light navbar-style">
    <div class="container-fluid">
        <a class="navbar-brand" href="/users/home">Dashboard</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation" style="border: transparent solid 1px; color: #1254a1; font-weight: bold">Categories</button>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
            <li class="nav-item dropdown nav nav-pills">
                <a class="navbar-brand dropdown-toggle" data-bs-toggle="dropdown" role="button" aria-expanded="false">Category</a>
                <ul class="dropdown-menu ">
                    <c:forEach items="${categories}" var="category">
                        <li><a class="dropdown-item" href="/users/${category.name}">${category.name}</a></li>
                    </c:forEach>
                </ul>
            </li>
        </div>
        <a class="navbar-brand" href="#">
            <i class="fas fa-heart"></i>
        </a>
        <a class="navbar-brand" href="/viewCart" >
            <i class="fas fa-shopping-cart"></i>
        </a>
        <a class="navbar-brand" href="/logout" >Log out</a>
    </div>
</nav>
<div class="container">
    <h1 class="mt-2">Shopping Cart</h1>
    <table class="table border border-3 border-dark text-center">
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
                <td>
                    <form:form action="/cart_item/${cartItem.id}/delete" method="delete">
                        <button class="btn btn-danger mx-1">Delete</button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p class="fs-4 text-end">Total Price: <span class="text-danger fw-bold">$${totalPrice}</span></p>
    <div class="d-flex gap-2 justify-content-end">
        <form action="/shippingDetails" method="post">
            <input type="submit" class="btn btn-primary" value="Next">
        </form>

        <a href="/users/home" class="btn btn-secondary">Back</a>
    </div>

</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

