<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Shopping Cart</h1>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Product Name</th>
            <th>Price</th>
            <th>Image</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cartItems}" var="cartItem">
            <tr>
                <td>${cartItem.product.name}</td>
                <td>${cartItem.product.price}</td>
                <td>
                    <img src="${cartItem.product.image}" alt="${cartItem.product.name}" width="100" height="100">
                </td>
                <td>
                    <form action="/removeCartItem" method="post">
                        <input type="hidden" name="productId" value="${cartItem.product.id}">
                        <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                    </form>
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





