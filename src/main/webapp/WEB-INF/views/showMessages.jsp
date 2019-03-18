<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>${showUser} messages</title>
    <style>
        .sub {
            color: black
        }
    </style>
</head>
<body>
<p>
<h1 align="center">${showUser} messages</h1></p>
<div>
    <c:choose>
        <c:when test="${not empty noMessages}">
            <div align="center">
                <p align="center">
                <h2><b style="color:darkred">${noMessages}</b></h2></p>
            </div>
        </c:when>
        <c:otherwise>
            <ul>
                <c:forEach items="${messageShowFormList}" var="message">
                    <li style="color: firebrick">Creator: <b>${message.messageCreator}</b>
                        <ul>
                            <li class="sub">Creation date-time: ${message.dateTimeCreation}</li>
                            <li class="sub">Topic: ${message.messageTopic}</li>
                            <li class="sub">Message text: ${message.messageBody}</li>
                        </ul>
                    </li>
                </c:forEach>
            </ul>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>