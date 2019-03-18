<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Success</title>
</head>
<body>
<div align="center">
    <p align="center">
    <h1>Success</h1> </p>
    <p align="center">
    <h3>${successMessage}</h3></p>
    <p><a href='<spring:url value="/home"></spring:url>'>go home</a></p>
    <p><a href='<spring:url value="/login"></spring:url>'>goto login</a></p>
</div>
</body>
</html>
