<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>user ${profileForm.name}</title>
    <style>
        li {
            list-style-type: none;
        }
        ul {
            margin-left: 0; /* Отступ слева в браузере IE и Opera */
            padding-left: 0; /* Отступ слева в браузере Firefox, Safari, Chrome */
        }
    </style>
</head>
<body>
    <div>
        <div align="center">
            <h2>user ${profileForm.name}</h2>
            <br>
            <ul>
                <li>Nickname = ${profileForm.name}</li>
                <c:if test="${not empty showEmail}">
                    <li>Email = ${profileForm.email}</li>
                </c:if>
                <li>Country = ${profileForm.country}</li>
                <li>Gender = ${profileForm.gender}</li>
                <li>
                    <a><b>~</b></a>
                </li>
                <li>
                    <a href='<spring:url value="/profile/${profileForm.name}/messages"></spring:url>'>
                    Show ${profileForm.name} messages</a>
                </li>
                <li>
                    <a><b>~</b></a>
                </li>
                <li>
                    <a href='<spring:url value="/sendMessage"></spring:url>'>Send message</a>
                </li>
                <li>
                    <a><b>~</b></a>
                </li>
                <li>
                    <a href='<spring:url value="/home"></spring:url>'>go home</a>
                </li>
            </ul>

        </div>
    </div>
</body>
</html>
