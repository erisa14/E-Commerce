<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html xmlns='http://www.w3.org/1999/xhtml' xmlns:th='http://www.thymeleaf.org'>
<head>
  <title>Result</title>
</head>
<body>
<h3 th:if='${error}' th:text='${error}' style='color: red;'></h3>
<div th:unless='${error}'>
  <h3 style='color: green;'>Success!</h3>
  <div>Id.: <span th:text='${id}' /></div>
  <div>Status: <span th:text='${status}' /></div>
  <div>Charge id.: <span th:text='${chargeId}' /></div>
  <div>Balance transaction id.: <span th:text='${balance_transaction}' /></div>
</div>
<a href='/checkout'>Checkout again</a>
</body>
</html>