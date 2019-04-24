<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <thead>
    <th>Email</th>
    <th>Salary</th>
    <th>Birth Date</th>
    <th colspan=2>Action</th>
    </thead>
    <tbody>
    <c:forEach items="${employees}" var="employee">
        <tr>
            <td>${employee.email}</td>
            <td>${employee.salary}</td>
            <td>${employee.birthDate}</td>
            <td>
                <a href="${pageContext.request.contextPath}/Controller?command=getToEditEmployee&employeeId=<c:out value="${employee.id}"/>">Edit</a>
            </td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/Controller">
                    <input type="hidden" name="employeeId" value="<c:out value="${employee.id}" />">
                    <input type="hidden" name="command" value="deleteEmployee"/>
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
