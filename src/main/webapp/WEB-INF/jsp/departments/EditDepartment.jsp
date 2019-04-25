<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/Controller" method="post">
    <table cellpadding="2" cellspacing="2">
        <tr>
            <td>Employee Id:</td>
            <td><input type="text" readonly="readonly" name="departmentId"
            <c:choose>
            <c:when test="${empty department.id}">
                       value="0"/></td>
            </c:when>
            <c:when test="${not empty department.id}">
                value="<c:out value="${department.id}"/>"/></td>
            </c:when>
            </c:choose> required></td>

        </tr>
        <tr>
            <td>Department Name:</td>
            <td><input type="text" name="name"
                       value="<c:out value="${department.name}" />" autofocus required/></td>
        </tr>
        <tr>
            <td><input type="hidden" name="command" value="addDepartment"/></td>
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
