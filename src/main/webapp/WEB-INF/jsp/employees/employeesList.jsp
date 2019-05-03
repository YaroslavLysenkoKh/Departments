<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <link href="../../../css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container">
    <p>
        <a href="getToEditEmployee?action=add" class="btn btn-primary" role="button">Add Employee</a>
    </p>
    <table class="table text-center" border="1">
        <thead class="thead-dark">
        <th scope="col" class="txt">Email</th>
        <th scope="col">Salary</th>
        <th scope="col">Birth Date</th>
        <th class="text-center" colspan=2>Action</th>
        </thead>
        <tbody>
        <c:forEach items="${employees}" var="employee">
            <tr>
                <td>${employee.email}</td>
                <td>${employee.salary}</td>
                <td>${employee.birthDate}</td>
                <td>
                    <a href="getToEditEmployee?action=update&employeeId=<c:out value="${employee.id}"/>"
                       class="btn btn-success" role="button">Edit</a>
                </td>
                <td>
                    <form method="post" action="/deleteEmployee" class="deleteForm">
                        <input type="hidden" name="employeeId" value="<c:out value="${employee.id}" />">
                        <input type="hidden" name="departmentId" value="<c:out value="${employee.departmentId}" />">
                        <input type="submit" value="Delete" class="btn btn-danger"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
