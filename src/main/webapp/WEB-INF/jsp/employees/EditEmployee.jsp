<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate var="date" value="${now}" pattern="yyyy-MM-dd"/>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/Controller" method="post">
    <table cellpadding="2" cellspacing="2">
        <tr>
            <td>Employee Id:</td>
            <td><input type="text" readonly="readonly" name="employeeId"
                       value="<c:out value="${employee.id}" />"/></td>
        </tr>
        <tr>
            <td>Employee Email:</td>
            <td><input type="text" name="email"
                       value="<c:out value="${employee.email}" />" autofocus required/></td>
        </tr>
        <tr>
            <td>Employee Salary:</td>
            <td><input type="text" name="price" value="${employee.salary}$" required></td>
        </tr>
        <tr>
            <td>Employee Password:</td>
            <td><input type="password" name="password" required></td>
        </tr>
        <tr>
            <td>Employee Birthday:</td>
            <td><input type="date" name="birthDate"
            <c:choose>
            <c:when test="${employee.id == 0}">
                       value="${date}"
            </c:when>
            <c:when test="${employee.id > 0}">
                       value="${employee.birthDate}"
            </c:when>
            </c:choose> required></td>
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
    <c:remove var="validationErrors" scope="session"/>
</form>

</body>
</html>
