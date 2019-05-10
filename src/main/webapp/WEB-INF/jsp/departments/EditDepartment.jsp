<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel='stylesheet' href='webjars/bootstrap/4.3.1/css/bootstrap.min.css'>
    <link href="../../../css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container">
    <%--    <c:set var="department" scope="request" value="${department}"/>--%>
    <form action="/addDepartment" method="post">
        <div class="form-addDepartment">
            <input type="hidden" name="departmentId"
                   value="<c:out value="${not empty department ? department.id : param.id}"/>">
            <label>Department Name</label>
            <input type="text" class="form-control" name="name"
                   value="<c:out value="${not empty department ? department.name : param.name}"/>"
                   placeholder="Enter name">
            <small class="text-danger">
                <c:out value="${validationErrors['name']}"/>
            </small>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
