<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shipping Details</title>
    <link rel="stylesheet" href="/css/stylee.css">
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


        /* Add background image */
        .background-image {
            background-image: url('/assets/plane.jpg');
            background-size: cover;
            background-repeat: no-repeat;
        }
    </style>
</head>
<body class="background-image font">
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

<div class="container">
    <h1>Shipping Details</h1>
    <form action="/shippingDetails" method="post" modelAttribute="shippingDetails">
        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" class="form-control" id="firstName" name="firstName">
        </div>

        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" class="form-control" id="lastName" name="lastName">
        </div>

        <div class="form-group">
            <label for="address1">Address Line 1:</label>
            <input type="text" class="form-control" id="address1" name="address1">
        </div>

        <div class="form-group">
            <label for="address2">Address Line 2:</label>
            <input type="text" class="form-control" id="address2" name="address2">
        </div>

        <div class="form-group">
            <label for="country">Country:</label>
            <input type="text" class="form-control" id="country" name="country">
        </div>

        <div class="form-group">
            <label for="city">City:</label>
            <input type="text" class="form-control" id="city" name="city">
        </div>

        <div class="form-group">
            <label for="zipCode">Zip Code:</label>
            <input type="text" class="form-control" id="zipCode" name="zipCode">
        </div>

        <div class="form-group">
            <label for="phoneNumber">Phone Number:</label>
            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber">
        </div>
    </form>

    <div class="d-flex gap-3 justify-content-end mt-2">
        <form action="/checkout" method="post" id="checkout-form">
            <input type="hidden" name="totalPrice" value="${totalPrice}">
            <input type="hidden" name="stripeToken" value=""> <!-- Leave the value empty -->

            <input type="submit" class="btn btn-primary" value="Next">
        </form>
        <a href="/viewCart" class="btn btn-secondary">Cancel</a>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>