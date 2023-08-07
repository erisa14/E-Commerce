<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shipping Details</title>
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
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" role="button" aria-expanded="false">Category</a>
                    <ul class="dropdown-menu">
                        <c:forEach items="${categories}" var="category">
                            <li><a class="dropdown-item" href="/admin/${category.name}">${category.name}</a></li>
                        </c:forEach>
                    </ul>
                </li>
            </ul>
        </div>
        <a class="navbar-brand" href="#" style="color: #1254a1">
            <i class="fas fa-heart"></i>
        </a>
        <a class="navbar-brand" href="#" style="color: #1254a1">
            <i class="fas fa-shopping-cart"></i>
        </a>
        <a class="navbar-brand" href="/logout" style="color: #1254a1; font-weight: bold">Log out</a>
    </div>
</nav>
<h1>Shipping Details</h1>
<form:form action="/shippingDetails" method="post" modelAttribute="shippingDetails">
    <form:label path="firstName">First Name:</form:label>
    <form:input path="firstName" /><br/>

    <form:label path="lastName">Last Name:</form:label>
    <form:input path="lastName" /><br/>

    <form:label path="address1">Address Line 1:</form:label>
    <form:input path="address1" /><br/>

    <form:label path="address2">Address Line 2:</form:label>
    <form:input path="address2" /><br/>

    <form:label path="country">Country:</form:label>
    <form:input path="country" /><br/>

    <form:label path="city">City:</form:label>
    <form:input path="city" /><br/>

    <form:label path="zipCode">Zip Code:</form:label>
    <form:input path="zipCode" /><br/>

    <form:label path="phoneNumber">Phone Number:</form:label>
    <form:input path="phoneNumber" /><br/>

    <input type="submit" value="Next" />
</form:form>

<a href="/viewCart">Cancel</a>

</body>
</html>

