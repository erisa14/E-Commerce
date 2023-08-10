<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>project</title>
    <!-- for Bootstrap CSS -->
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

        /* BUTTON classes */

        /* pics */
        .view-product {
            border: 2px solid #1254a1;
            border-radius: 10px;
            margin: 0px 20px 20px 0px;
            padding: 15px 10px 0px 10px;
            height: 100%;
        }
        .view-img {
            border: 1.5px solid #1254a1;
            border-radius: 10px;
            width: 200px;
            height: 200px;
            padding: 20px;
        }
    </style>

</head>
<body style="background-color: #f5fbff;">
<!-- NAV BAR -->
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

<div class="my-container">


    <!-- HEADER -->
    <c:set var="lastCategory" value="" />
    <c:forEach items="${categories}" var="category">
        <c:forEach var="product" items="${products}">
            <c:if test="${category.name eq product.category.name && lastCategory ne category.name}">
                <div class="header justify-center">
                    <c:choose>
                        <c:when test="${product.category.name eq 'laptop'}">
                            Laptops
                        </c:when>
                        <c:when test="${product.category.name eq 'cellphone'}">
                            Cellphones
                        </c:when>
                        <c:when test="${product.category.name eq 'pc'}">
                            PCs
                        </c:when>
                    </c:choose>
                </div>
                <c:set var="lastCategory" value="${category.name}" />
            </c:if>
        </c:forEach>
    </c:forEach>






    <!-- PRODUCTS -->
    <div class="d-flex">
            <c:forEach var="product" items="${products}">
                <div class="view-product">
                    <a class="ripple nav-link" href="#">
                        <img src="${product.image}" class="view-img img-fluid rounded" alt="${product.productName}"/>
                    </a>
                    <div class="d-flex">
                        <div class="col">
                            <a class="justify-center" href="#">${product.productName}</a>
                            <p class="justify-center">$${product.price}</p>
                        </div>
                        <div class="col">
                            <form action="/new_wishlist_item" method="post">
                                <input type="hidden" name="productId" value="${product.id}">
                                <button type="submit" class="btn btn-light">
                                    <i class="fas fa-heart" style="color: #1254a1;"></i>
                                </button>
                            </form>

                            <form action="/new_cart_item" method="post">
                                <input type="hidden" name="productId" value="${product.id}">
                                <button type="submit" class="btn btn-light">
                                    <i class="fas fa-shopping-cart" style="color: #1254a1;"></i>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
    </div>

</div>
</body>
</html>