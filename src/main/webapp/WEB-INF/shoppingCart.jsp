<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
<h1>Shopping Cart</h1>
<table border="1">
    <tr>
        <th>Product Name</th>
        <th>Price</th>
        <th>Image</th>
    </tr>
    <c:forEach items="${cartItems}" var="cartItem">
        <tr>
            <td>${cartItem.product.name}</td>
            <td>${cartItem.product.price}</td>
            <td>
                <img src="${cartItem.product.image}" alt="${cartItem.product.name}" width="100" height="100">
            </td>
        </tr>
    </c:forEach>
</table>
<form action="/shippingDetails" method="get">
    <input type="submit" class="btn btn-primary" value="Next">
</form>
<a href="/">Cancel</a>
</body>
</html>
