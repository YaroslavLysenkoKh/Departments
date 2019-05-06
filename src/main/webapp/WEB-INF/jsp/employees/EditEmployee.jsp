<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <link rel='stylesheet' href='webjars/bootstrap/4.3.1/css/bootstrap.min.css'>
    <link href="../../../css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container">
    <form action="/addEmployee" method="post">
        <input type="hidden" name="employeeId" value="<c:out value="${employee.id}"/>">
        <div class="form-addEmployee">
            <label>Email address</label>
            <input type="email" class="form-control" name="email" value="<c:out value="${employee.email}"/>"
                   placeholder="Enter email">
            <small class="form-text text-muted text-danger">
                <c:out value="${validationErrors['email']}"/>
            </small>
        </div>
        <div class="form-addEmployee">
            <label>Salary</label>
            <input type="text" class="form-control" value="<c:out value="${employee.salary}"/>"
                   placeholder="Salary" name="salary">
            <small class="form-text text-muted text-danger">
                <c:out value="${validationErrors['salary']}"/>
            </small>
        </div>
        <div class="form-addEmployee">
            <label>Birth Date</label>
            <input type="date" class="form-control"
                   value="<c:out value="${employee.birthDate}" />" placeholder="BirthDate" name="birthDate">
            <small class="form-text text-muted text-danger">
                <c:out value="${validationErrors['birthDate']}"/>
            </small>
        </div>
        <div class="form-addEmployee">
            <label>Department select</label>
            <select class="form-control" name="departmentId">
                <c:forEach var="department" items="${departments}">
                    <option value="${department.id}">${department.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
