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
        <div class=" form-addEmployee">
            <input type="hidden" name="employeeId"
                   value="<c:out value="${param['employeeId'] eq null ? employee.id : param['employeeId']}"/>" readonly>
        </div>
        <div class=" form-addEmployee">
            <label>Email address</label>
            <input type="email" class="form-control" name="email"
                   value="<c:out value="${not empty employee ? employee.email : param.email}"/>"
                   placeholder="Enter email">
            <small class="text-danger">
                <c:out value="${validationErrors['email']}"/>
            </small>
        </div>
        <div class="form-addEmployee">
            <label>Salary</label>
            <input type="text" class="form-control"
                   value="<c:out value="${not empty employee ? employee.salary : param.salary}"/>"
                   placeholder="Salary" name="salary">
            <small class="text-danger">
                <c:out value="${validationErrors['salary']}"/>
            </small>
        </div>
        <div class="form-addEmployee">
            <label>Birth Date</label>
            <input type="date" class="form-control"
                   value="<c:out value="${not empty employee ? employee.birthDate : param.birthDate}"/>"
                   placeholder="BirthDate" name="birthDate">
            <small class="text-danger">
                <c:out value="${validationErrors['birthDate']}"/>
            </small>
        </div>
        <c:choose>
            <c:when test="${empty param['employeeId']}">
                <input type="hidden" name="departmentId"
                       value="<c:out value="${not empty employee ? departmentId : param.departmentId}"/>">
            </c:when>
            <c:otherwise>
                <div class="form-addEmployee">
                    <label>Department select</label>
                    <select class="form-control" name="departmentId">
                        <c:forEach var="department" items="${departments}">
                            <c:choose>
                                <c:when test="${department.id == employee.departmentId}">
                                    <option selected value="${department.id}">${department.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${department.id}">${department.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </c:otherwise>
        </c:choose>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
