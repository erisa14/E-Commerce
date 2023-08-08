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
        .justify-right {                    /*          right*/
            display: flex;
            align-items: flex-end;
            justify-content: flex-end;
            margin-bottom: 20px;
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

        /* IMAGE classes */
        .view-image {
            width: 70%;
            height: 250px;
            border: solid 2px #1254a1;
            color: #1254a1;
            margin: 0 auto;
        }
    </style>

</head>
<body style="background-color: #f5fbff;">
<div class="my-container">
    <!-- NAV BAR -->
    <nav class="navbar navbar-expand-lg navbar-light navbar-background">
        <div class="container-fluid">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation" style="border: transparent solid 1px; color: #1254a1; font-weight: bold">Categories</button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="/users/home" style="color: #1254a1">All products</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/users/laptop" style="color: #1254a1">Laptops</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/users/cellphone" style="color: #1254a1">Cellphones</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/users/pc" style="color: #1254a1">PCs</a>
                    </li>
                </ul>
            </div>

            <form class="d-flex input-group w-auto" method="post" action="/search">
                <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" name="searchTerm"/>
                <span class="input-group-text border-0" id="search-addon">
                    <i class="fas fa-search"></i>
                </span>
            </form>

            <a class="navbar-brand" href="#" style="color: #1254a1">
                <i class="fas fa-heart"></i>
            </a>
            <a class="navbar-brand" href="#" style="color: #1254a1">
                <i class="fas fa-shopping-cart"></i>
            </a>
            <a class="navbar-brand" href="/logout" style="color: #1254a1; font-weight: bold">Log out</a>
        </div>
    </nav>




    <!-- HEADER -->
    <div class="header justify-center">Hello ${user.firstname}</div>




    <!-- CAROUSEL -->
    <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel" style="margin-bottom: 40px">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <div class="view-image" onclick="location.href='/users/${categories[0].name}'"><img src="..." class="d-block w-100" alt="${categories[0].name}"></div>
            </div>
            <c:forEach var="category" items="${categories}" begin="1">
                <div class="carousel-item">
                    <div class="view-image" onclick="location.href='/users/${category.name}'"><img src="..." class="d-block w-100" alt="${category.name}"></div>
                </div>
            </c:forEach>
        </div>

        <button style="background-color: #90caf9;" class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
            <span style="background-color: transparent;" class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span>Previous</span>       <!-- class="visually-hidden"-->
        </button>
        <button style="background-color: #90caf9;" class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
            <span style="background-color: transparent;" class="carousel-control-next-icon" aria-hidden="true"></span>
            <span>Next</span>       <!-- class="visually-hidden"-->
        </button>
    </div>




    <!-- PRODUCTS -->
    <div class="d-flex">
        <c:set var="lastCategory" value="" />

        <c:forEach items="${categories}" var="category">
            <c:forEach var="product" items="${products}">
                <c:if test="${category.name eq product.category.name && lastCategory ne category.name}">
                    <div class="view-product">
                        <a class="ripple nav-link" href="#">
                            <img src="${product.image}" class="view-img img-fluid rounded" alt="${product.productName}"/>
                        </a>
                        <div class="d-flex">
                            <div class="col">
                                <a class="justify-center" href="#">${product.productName}</a>
                                <a class="justify-center" href="/users/${product.category.name}">${product.category.name}</a>
                                <p class="justify-center">$${product.price}</p>
                            </div>
                            <div class="col">
                                    <i class="fas fa-heart navbar-brand justify-center" style="color: #1254a1"></i>
                                <form action="/new_cart_item" method="post">
                                    <button type="submit" class="btn btn-light">
                                        <i class="fas fa-shopping-cart" style="color: #1254a1;"></i>
                                    </button>
                                </form>
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