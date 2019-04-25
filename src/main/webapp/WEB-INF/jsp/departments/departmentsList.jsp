<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table/>
<table border="1">
    <thead>

    <th>Name</th>
    <th colspan=3>Action</th>

    </thead>
    <tbody>
    <c:forEach items="${departments}" var="department">
        <tr>
            <td><c:out value="${department.name}"></c:out></td>
            <td>
                <a href="Controller?command=departmentEmployees&action=departmentEmployee&departmentId${department.id}">Employee
                    List</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/Controller?command=getToEditDepartment&departmentId=<c:out value="${department.id}"/>">Edit</a>
            </td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/Controller">
                    <input type="hidden" name="departmentId" value="<c:out value="${department.id}" />">
                    <input type="hidden" name="command" value="deleteDepartment"/>
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
