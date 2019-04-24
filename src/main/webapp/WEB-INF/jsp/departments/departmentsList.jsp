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
    <th colspan=2>Action</th>

    </thead>
    <tbody>
    <c:forEach items="${departments}" var="department">
        <tr>
            <td>${department.name}</td>
            <td><a href="Controller?command=departmentEmployees&departmentId${department.id}"></a>Employee List</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
