<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>hello</title>
</head>
<body>
<h1>Spring MVC Hello World Example</h1>

<h2>${msg}</h2>

<c:forEach items="${usersList}" var="element">
    <tr>
        <td>${element}</td>
    </tr>
</c:forEach>
<br>
<br>
<c:forEach items="${messagesList}" var="el2">
    <tr>
        <td>${el2}}</td>
    </tr>
</c:forEach>


</body>
</html>
