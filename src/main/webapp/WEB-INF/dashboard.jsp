<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<div class="container">
    <h1>Welcome ${user.username}!</h1>
    <h1>Welcome ${user.firstname}!</h1>

    <p>This is your dashboard. Nothing to see here yet.</p>
    <a href="/logout">Logout</a>
</div>
</body>
</html>