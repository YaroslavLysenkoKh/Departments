package com.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;


public interface Command extends Serializable {
    String FORWARD_EDIT_DEPARTMENT_PAGE = "/WEB-INF/jsp/departments/EditDepartment.jsp";
    String FORWARD_DEPARTMENTS_PAGE = "/WEB-INF/jsp/departments/DepartmentsList.jsp";
    String REDIRECT_TO_EMPLOYEES_LIST = "departmentEmployees?action=departmentEmployee&departmentId=";
    String FORWARD_EMPLOYEES_PAGE = "/WEB-INF/jsp/employees/EmployeesList.jsp";
    String FORWARD_EDIT_EMPLOYEE_PAGE = "/WEB-INF/jsp/employees/EditEmployee.jsp";

    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}