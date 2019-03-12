<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="parent">
    <div align="center" style="margin-bottom: 50%">
        <h1>Login</h1>
        <form:form action="login" modelAttribute="loginForm" method="post">
            <table>
                <tr>
                    <td><form:label path="name">Nickname</form:label></td>
                    <td><form:input path="name" id="nickname" /></td>
                    <td><form:errors path="name" cssClass="error" /></td>
                </tr>
                <tr>
                    <td><form:label path="password">Password</form:label></td>
                    <td><form:password path="password" id="password" /></td>
                    <td><form:errors path="password" cssClass="error"/></td>
                </tr>
                <tr>
                    <td>--------------</td>
                </tr>
                <tr>
                    <td align="right"><input type="submit" value="Submit"></td>
                </tr>
            </table>
        </form:form>
        <a href='<spring:url value="/registration"></spring:url>'>goto Registration</a>
    </div>
</div>
</body>
</html>
