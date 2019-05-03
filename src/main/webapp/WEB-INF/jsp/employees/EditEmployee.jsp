<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <link href="../../../css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container">
    <form action="/addEmployee" method="post">
        <input type="hidden" name="command" value="addEmployee"/>
        <div class="form-addEmployee">
            <label for="employeeId">Employee Id</label>
            <input type="text" class="form-control" id="employeeId" name="employeeId" readonly="readonly"
                    <c:choose>
                        <c:when test="${empty employee.id}">
                            value=""
                        </c:when>
                        <c:when test="${not empty employee.id}">
                            value="<c:out value="${employee.id}"/>"
                        </c:when>
                    </c:choose>
            />
        </div>
        <div class="form-addEmployee">
            <label for="employeeEmail">Email address</label>
            <input type="email" class="form-control" id="employeeEmail" name="email" aria-describedby="emailHelp"
                   value="<c:out value="${employee.email}" />" placeholder="Enter email">
            <small id="emailHelp" class="form-text text-muted">
                <c:choose>
                    <c:when test="${not empty validationErrors['email']}">
                        <p style="color:red"><c:out value="${validationErrors['email']}"/></p>
                    </c:when>
                </c:choose>
            </small>
        </div>
        <div class="form-addEmployee">
            <label for="employeePassword">Password</label>
            <input type="password" class="form-control" id="employeePassword" placeholder="Password" name="password">
            <small id="passwordHelp" class="form-text text-muted">
                <c:choose>
                    <c:when test="${not empty validationErrors['password']}">
                        <p style="color:red"><c:out value="${validationErrors['password']}"/></p>
                    </c:when>
                </c:choose>
            </small>
        </div>
        <div class="form-addEmployee">
            <label for="employeeSalary">Salary</label>
            <input type="text" class="form-control" id="employeeSalary" value="<c:out value="${employee.salary}" />"
                   placeholder="Salary" name="salary">
            <small id="salaryHelp" class="form-text text-muted">
                <c:choose>
                    <c:when test="${not empty validationErrors['salary']}">
                        <p style="color:red"><c:out value="${validationErrors['salary']}"/></p>
                    </c:when>
                </c:choose>
            </small>
        </div>
        <div class="form-addEmployee">
            <label for="employeeBirthDate">Birth Date</label>
            <input type="date" class="form-control" id="employeeBirthDate" placeholder="BirthDate" name="birthDate">
            <small id="birthDateHelp" class="form-text text-muted">
                <c:choose>
                    <c:when test="${not empty validationErrors['birthDate']}">
                        <p style="color:red"><c:out value="${validationErrors['birthDate']}"/></p>
                    </c:when>
                </c:choose>
            </small>
        </div>
        <div class="form-addEmployee">
            <label for="deprtmentsList">Department select</label>
            <select class="form-control" id="deprtmentsList" name="departmentId">
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
