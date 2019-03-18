<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Message success</title>
</head>
<body>
<div align="center">
    <p align="center">
    <h1>Message was successfully sent</h1></p>
    <br><br>
    <p align="center">
    <h3><a href='<spring:url value="/sendMessage"></spring:url>'>send one more message</a></h3></p>
    <br><br>
    <p align="center">
    <h3><a href='<spring:url value="/home"></spring:url>'>goto home</a></h3></p>
</div>
</body>
</html>
