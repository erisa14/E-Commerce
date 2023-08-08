<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Read Share</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>


<div class="card" style="width: 18rem;">
  <img src="/assets/${product.image}" class="card-img-top" alt="...">
  <div class="card-body">
    <h5 class="card-title">Card title</h5>
    <p class="card-text">${product.description}</p>
    <p class="card-text">${product.price}</p>
    <p class="card-text">${product.brand}</p>
    <p class="card-text">${product.color}</p>
    <p class="card-text">${product.quantity}</p>
  </div>
</div>


</body>
</html>