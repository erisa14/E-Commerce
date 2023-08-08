<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
    <table class="table table-striped border border-3 border-dark text-center">
        <thead>
        <tr>
            <th>OrderId</th>
            <th>Date of order</th>
            <th>Total Price</th>
            <th>Action</th>
        </tr>
        </thead>
    <tbody>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.id}</td>
            <td>${order.orderDate}</td>
            <td>${order.orderItem}</td>
            <td>Confirm</td>
        </tr>
    </c:forEach>
    </tbody>
    </table>

    <a href="/logout">Logout</a>

</body>
</html>
