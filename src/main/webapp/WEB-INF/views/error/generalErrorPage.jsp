<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Error page</title>
</head>
<body>
<div align="center">
    <p>
    <h1>${fromController}</h1></p>
    <p>
    <h1>${exceptionIs.getMessage()}</h1></p>
    <p>
    <h1></h1></p>

    <p align="center"><a href='<spring:url value="/home"></spring:url>'>goto Home</a></p>


    <c:if test="${not empty sessionUserData.userName}">
        <p align="center"><a href='<spring:url value="/logout"></spring:url>'>goto Logout</a></p>
        <p align="center"><a href='<spring:url value="/profile/${sessionUserData.userName}"></spring:url>'>goto Profile</a></p>
        <p align="center"><a href='<spring:url value="/sendMessage"></spring:url>'>goto SendMessage</a></p>

    </c:if>
    <c:if test="${empty sessionUserData.userName}">
        <p align="center"><a href='<spring:url value="/registration"></spring:url>'>goto Registration</a></p>
        <p align="center"><a href='<spring:url value="/login"></spring:url>'>goto Login</a></p>
    </c:if>
</div>
</body>
</html>
