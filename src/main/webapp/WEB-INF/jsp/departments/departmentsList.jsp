<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <link href="../../../css/style.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div class="container ">
    <p style="color:red">${errorMessage}</p>
    <p>
        <a href="getToEditDepartment?action=add" class="btn btn-primary" role="button">Add Department</a>
    </p>
    <table class="table text-center" border="1">
        <thead class="thead-dark">
        <th class="text-center">Name</th>
        <th class="text-center" colspan=3>Action</th>

        </thead>
        <tbody>
        <c:forEach items="${departments}" var="department">
            <tr>
                <td><c:out value="${department.name}"></c:out></td>
                <td>
                    <a href="departmentEmployees?departmentId=<c:out value="${department.id}"/>"
                       class="btn btn-success" role="button">Employee
                        List</a>
                </td>
                <td>
                    <a href="getToEditDepartment?action=update&departmentId=<c:out value="${department.id}"/>"
                       class="btn btn-warning" role="button">Edit</a>
                </td>
                <td>
                    <form method="post" action="/deleteDepartment">
                        <input type="hidden" name="departmentId" value="<c:out value="${department.id}" />">
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
