<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shipping Details</title>
</head>
<body>
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

