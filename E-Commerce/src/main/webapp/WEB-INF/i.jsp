<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page isErrorPage="true" %>

<html>
<head>
    <title>Login/Register</title>
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body class="mx-auto">
<div class="container">
    <h1>Welcome</h1>
    <h2>Join our growing community</h2>
    <div>
        <table class="table table-striped-columns table-bordered form border border-dark border-3">
            <thead>
            <tr>
                <td colspan="2">
                    <h3 class="text-center">Register</h3>
                </td>
            </tr>
            </thead>
            <tbody>
            <form:form modelAttribute="newUser" action="/register" method="post" class="form gap-3 d-flex flex-column">
            <tr>
                <td class="col-sm-6">
                    <form:label path="username" class="ms-2 fw-semibold col-form-label">Username</form:label></td>
                <td>
                    <form:errors path="username" class="errors text-danger"/>
                    <form:input path="username" type="text" class="form-control"/>
                </td>
            </tr>
            <tr>
                <td class="col-sm-6">
                    <form:label path="firstname" class="ms-2 fw-semibold col-form-label">FirstName</form:label></td>
                <td>
                    <form:errors path="firstname" class="errors text-danger"/>
                    <form:input path="firstname" type="text" class="form-control"/>
                </td>
            </tr>
            <tr>
                <td class="col-sm-6">
                    <form:label path="lastname" class="ms-2 fw-semibold col-form-label">Lastname</form:label></td>
                <td>
                    <form:errors path="lastname" class="errors text-danger"/>
                    <form:input path="lastname" type="text" class="form-control"/>
                </td>
            </tr>
            <tr>
                <td class="col-sm-6">
                    <form:label path="email" class="ms-2 fw-semibold col-form-label">Email</form:label>
                </td>
                <td>
                    <form:errors path="email" class="errors text-danger"/>
                    <form:input path="email" type="text" class="form-control"/>
                </td>
            </tr>
            <tr>
                <td class="col-sm-6">
                    <form:label path="address" class="ms-2 fw-semibold col-form-label">Address</form:label></td>
                <td>
                    <form:errors path="address" class="errors text-danger"/>
                    <form:input path="address" type="text" class="form-control"/>
                </td>
            </tr>
            <tr>
                <td class="col-sm-6">
                    <form:label path="phoneNumber" class="ms-2 fw-semibold col-form-label">Phone Number</form:label></td>
                <td>
                    <form:errors path="phoneNumber" class="errors text-danger"/>
                    <form:input path="phoneNumber" type="phone" class="form-control"/>
                </td>
            </tr>
            <tr>
                <td class="col-sm-6">
                    <form:label path="password" class="ms-2 fw-semibold col-form-label">Password</form:label>
                </td>
                <td>
                    <form:errors path="password" class="errors text-danger"/>
                    <form:input path="password" type="password" class="form-control"/></td>
            </tr>
            <tr>
                <td class="col-sm-6">
                    <form:label path="confirm" class="ms-2 fw-semibold col-form-label">Confirm Password</form:label>
                </td>
                <td>
                    <form:errors path="confirm" class="errors text-danger"/>
                    <form:input path="confirm" type="password" class="form-control"/>
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="2">
                    <input class="form-control btn btn-primary" type="submit" value="Submit">
                </td>
            </tr>
            </tfoot>
            </form:form>
        </table>
        <table class="table table-striped-columns table-bordered form border border-dark border-3">
            <thead>
            <tr>
                <td colspan="2">
                    <h3 class="text-center">Log In</h3>
                </td>
            </tr>
            </thead>
            <tbody>
            <form:form modelAttribute="newLogin" action="/login" method="post" class="form gap-3 d-flex flex-column">
            <tr>
                <td class="col-sm-6">
                    <form:label path="email" class="ms-2 fw-semibold col-sm-5 col-form-label">Email</form:label>
                </td>
                <td>
                    <form:errors path="email" class="errors text-danger"/>
                    <form:input path="email" type="text" class="form-control"/>
                </td>
            </tr>
            <tr>
                <td class="col-sm-6">
                    <form:label path="password" class="ms-2 fw-semibold col-sm-5 col-form-label">Password</form:label>
                </td>
                <td>
                    <form:input path="password" type="password" class="form-control"/>
                    <form:errors path="password" class="errors text-danger"/>
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="2">
                    <input class="form-control btn btn-primary" type="submit" value="Submit">
                </td>
            </tr>
            </tfoot>
            </form:form>
        </table>
    </div>
</div>
</body>
</html>