<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>home</title>
</head>
<body>
<div align="center">
    <p align="center">
    <h1>home</h1></p>

    <c:if test="${not empty userName}">
        <p align="center">
        <h2>${userName}</h2></p>
        <p align="center">
        <h2>${userIp}</h2></p>
        <p align="center"><a href='<spring:url value="/logout"></spring:url>'>goto Logout</a></p>
        <p align="center"><a href='<spring:url value="/profile/${userName}"></spring:url>'>goto Profile</a></p>
        <p align="center"><a href='<spring:url value="/sendMessage"></spring:url>'>goto SendMessage</a></p>

    </c:if>
    <c:if test="${empty userName}">
        <p align="center"><a href='<spring:url value="/registration"></spring:url>'>goto Registration</a></p>
        <p align="center"><a href='<spring:url value="/login"></spring:url>'>goto Login</a></p>
    </c:if>
</div>
</body>
</html>
