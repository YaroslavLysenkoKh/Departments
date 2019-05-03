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
            <input type="hidden" name="departmentId" value="<c:out value="${department.id}" />">
            <label>Department Name</label>
            <input type="text" class="form-control" name="name"
                   value="<c:out value="${department.name}" />" placeholder="Enter name">
            <small class="form-text text-muted text-danger">
                <c:out value="${validationErrors['name']}"/>
            </small>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
