<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
    <link rel='stylesheet' href='webjars/bootstrap/4.3.1/css/bootstrap.min.css'>
    <link href="../../css/style.css" rel="stylesheet" type="text/css"/>
    <script src="script/DeleteIsSatisfied.js"></script>
</head>
<body>
<div class="container ">
    <p>
        <a href="department" class="btn btn-primary" role="button">Add Department</a>
    </p>
    <table class="table text-center" border="1">
        <thead class="thead-dark">
        <th class="text-center">Name</th>
        <th class="text-center" colspan=3>Action</th>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${empty departments}">
                <td colspan="2">Empty List</td>
            </c:when>
            <c:otherwise>
                <c:forEach items="${departments}" var="department">
                    <tr>
                        <td><c:out value="${department.name}"></c:out></td>
                        <td>
                            <a href="employee/<c:out value="${department.id}"/>"
                               class="btn btn-success" role="button">Employee List</a>
                        </td>
                        <td>
                            <a href="department/<c:out value="${department.id}"/>"
                               class="btn btn-warning" role="button">Edit</a>
                        </td>
                        <td>
                            <form method="post" action="/department/delete">
                                <input type="hidden" name="departmentId" value="<c:out value="${department.id}" />">
                                <input type="submit" value="Delete" class="btn btn-danger"
                                       onclick="return isSatisfied(${fn:length(department.employeeList)}, this.form)"/>
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
