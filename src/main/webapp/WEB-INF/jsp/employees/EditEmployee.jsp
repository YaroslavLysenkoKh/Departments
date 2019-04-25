<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>

</head>
<body>

<form action="${pageContext.request.contextPath}/Controller" method="post">
    <table cellpadding="2" cellspacing="2">
        <tr>
            <td>Employee Id:</td>
            <td><input type="text" readonly="readonly" name="employeeId"
            <c:choose>
            <c:when test="${empty employee.id}">
                       value="0"/></td>
            </c:when>
            <c:when test="${not empty employee.id}">
                value="<c:out value="${employee.id}"/>"/></td>
            </c:when>
            </c:choose></td>

        </tr>
        <tr>
            <td>Employee Email:</td>
            <td><input type="text" name="email"
                       value="<c:out value="${employee.email}" />" autofocus required/></td>
        </tr>
        <tr>
            <td>Employee Salary:</td>
            <td><input type="text" name="price" value="${employee.salary}" required></td>
        </tr>
        <tr>
            <td>Employee Password:</td>
            <td><input type="password" name="password" required></td>
        </tr>
        <tr>
            <td>Employee Birthday:</td>
            <td><input type="date" name="birthDate" value="${employee.birthDate}"></td>
        </tr>
        <tr>
            <td>Departments:</td>
            <td><select name="departmentId">
                <c:forEach var="department" items="${departments}">
                    <option value="${department.id}">${department.name}</option>
                </c:forEach>
            </select></td>

        </tr>
        <tr>
            <td><input type="hidden" name="command" value="addEmployee"/></td>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
    <c:forEach var="item" items="${validationErrors}">
        <p style="color:red">${item}</p>
    </c:forEach>
    <c:remove var="validationErrors" scope="request"/>
</form>

</body>
</html>
