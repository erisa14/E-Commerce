<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>${product.productName}</title>
  <link rel="stylesheet" href="/css/stylee.css">
  <!-- for Bootstrap CSS -->
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
  </style>

</head>
<body class="font">
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



<div class="card w-25 mt-3 mx-auto">
  <div class="d-flex">

    <div class="col">
      <h3 class="card-title mt-1">${product.productName}</h3>
      <img src="/assets/${product.image}" class="card-img-top" alt="...">
    </div>

    <div class="card-body col">
      <p class="card-text">Price: $${product.price}</p>
      <p class="card-text">Brand: ${product.brand}</p>
      <p class="card-text" type="color">Color: ${product.color}</p>
      <p class="card-text">Description: ${product.description}</p>
      <div class="d-flex justify-content-end">
        <form action="/new_wishlist_item" method="post">
          <input type="hidden" name="productId" value="${product.id}">
          <button type="submit" class="btn">
            <i class="fas fa-heart" style="color: #1254a1;"></i>
          </button>
        </form>
        <form action="/new_cart_item" method="post">
          <input type="hidden" name="productId" value="${product.id}">
          <button type="submit" class="btn">
            <i class="fas fa-shopping-cart" style="color: #1254a1;"></i>
          </button>
        </form>
        <a href="/users/home" class="btn btn-outline-secondary">Back</a>

      </div>
    </div>
  </div>
</div>


</body>
</html>