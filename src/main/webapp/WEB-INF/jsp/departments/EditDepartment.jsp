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
    <form action="/addDepartment" method="post">
        <div class="form-addDepartment">
            <label for="departmentId">Department Id</label>
            <input type="text" class="form-control" id="departmentId" name="departmentId" readonly="readonly"
                    <c:choose>
                        <c:when test="${empty department.id}">
                            value=""
                        </c:when>
                        <c:when test="${not empty department.id}">
                            value="<c:out value="${department.id}"/>"
                        </c:when>
                    </c:choose>
            />
        </div>
        <div class="form-addDepartment">
            <label for="departmentName">Department Name</label>
            <input type="text" class="form-control" id="departmentName" name="name"
                   value="<c:out value="${department.name}" />" placeholder="Enter name">
            <small id="emailHelp" class="form-text text-muted">
                <c:choose>
                    <c:when test="${not empty validationErrors['name']}">
                        <p style="color:red"><c:out value="${validationErrors['name']}"/></p>
                    </c:when>
                </c:choose>
            </small>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        <c:remove var="validationErrors" scope="request"/>
    </form>
</div>
</body>
</html>
