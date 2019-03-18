<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Registration</title>

    <style>
        .error {
            color: green;
            font-weight: bold;
        }

        .parent {

        }
    </style>
</head>
<body>
<div class="parent">
    <div align="center" style="margin-bottom: 50%">
        <h1>Registration form</h1>
        <form:form action="registration" modelAttribute="registrationForm" method="post">
            <table>
                <tr>
                    <td><form:label path="name">Nickname</form:label></td>
                    <td><form:input path="name" id="nickname"/></td>
                </tr>
                <tr>
                    <td><form:label path="email">Email</form:label></td>
                    <td><form:input path="email" id="email"/></td>
                </tr>
                <tr>
                    <td><form:label path="password">Password</form:label></td>
                    <td><form:password path="password" id="password"/></td>
                </tr>
                <tr>
                    <td>Gender</td>
                    <td>
                        <form:radiobutton path="gender" value="Male"/>Male
                        <form:radiobutton path="gender" value="Female"/>Female
                        <form:radiobutton path="gender" value="Other"/>Other
                    </td>
                </tr>
                <tr>
                    <td>Country</td>
                    <td><form:select path="country" items="${countryList}"/></td>
                </tr>
                <tr>
                    <td>--------------</td>
                </tr>
                <tr>
                    <td align="right"><input type="submit" value="Submit"></td>
                </tr>
                <tr>
                    <td>--------------</td>
                </tr>
                <tr>
                    <td><form:errors path="name" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><form:errors path="email" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><form:errors path="password" cssClass="error"/></td>
                </tr>
                <tr>
                    <td>--------------</td>
                </tr>
            </table>

        </form:form>
        <a href='<spring:url value="/login"></spring:url>'>goto Login</a>
    </div>
</div>
</body>
</html>
