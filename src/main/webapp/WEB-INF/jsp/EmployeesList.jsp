<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel='stylesheet' href='webjars/bootstrap/4.3.1/css/bootstrap.min.css'>
    <link href="../../css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="mx-auto" style="width: 200px;">
    <p>
        <a href="/" class="btn btn-link" role="button">Back To Departments</a>
    </p>
</div>
<div class="container">
    <p>
        <a href="empForm/<c:out value="${departmentId}" />" class="btn btn-primary"
           role="button">Add
            Employee</a>
    </p>
    <table class="table text-center" border="1">
        <thead class="thead-dark">
        <th scope="col" class="txt">Email</th>
        <th scope="col">Salary</th>
        <th scope="col">Birth Date</th>
        <th class="text-center" colspan=2>Action</th>
        </thead>

        <tbody>
        <c:choose>
            <c:when test="${empty employees}">
                <td colspan="4">Empty List</td>
            </c:when>
            <c:otherwise>
                <c:forEach items="${employees}" var="employee">
                    <tr>
                        <td>${employee.email}</td>
                        <td>${employee.salary}</td>
                        <td>${employee.birthDate}</td>
                        <td>
                            <a href="getToEditEmployee/<c:out value="${employee.id}"/>"
                               class="btn btn-success" role="button">Edit</a>
                        </td>
                        <td>
                            <form method="post" action="/deleteEmployee" class="deleteForm">
                                <input type="hidden" name="employeeId" value="<c:out value="${employee.id}" />">
                                <input type="hidden" name="departmentId"
                                       value="<c:out value="${employee.department.id}" />">
                                <input type="submit" value="Delete" class="btn btn-danger"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
</body>
</html>