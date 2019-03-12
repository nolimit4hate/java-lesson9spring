<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Message</title>
</head>
<body>
<div align="center">
    <form:form action="processMessage" modelAttribute="messageSendForm" method="post">
        <table>
            <tr>
                <td align="center">Enter topic of post</td>
            </tr>
            <tr>
                <td><form:textarea path="messageTopic" cols="50" rows="2" /> </td>
            </tr>
            <tr>
                <td align="center">Enter body of post</td>
            </tr>
            <tr>
                <td><form:textarea path="messageBody" cols="50" rows="10" /> </td>
            </tr>
            <tr>
                <td align="right"><input type="submit" value="Submit"></td>
            </tr>
            <tr>
                <td></td>
            </tr>
            <tr>
                <td align="center"><a href='<spring:url value="/home"></spring:url>'>goto home</a></td>
            </tr>
        </table>
    </form:form>
</div>
<div>

</div>
</body>
</html>
