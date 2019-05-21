package com.command.impl.employees;

import com.command.Command;
import com.entity.Employee;
import com.exception.ValidationException;
import com.extractor.RequestExtractor;
import com.extractor.employee.EmployeeHttpRequestExtractor;
import com.service.departments.DepartmentService;
import com.service.employee.EmployeeService;
import com.service.impl.DepartmentHiberSerivceImpl;
import com.service.impl.EmployeeHiberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeAddOrEditCommand implements Command {
    private RequestExtractor<Employee> employeeRequestExtractor;
    private EmployeeService employeeService;
    private DepartmentService departmentService;

    public EmployeeAddOrEditCommand() {
        this.employeeRequestExtractor = new EmployeeHttpRequestExtractor();
        this.employeeService = new EmployeeHiberServiceImpl();
        this.departmentService = new DepartmentHiberSerivceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Employee employee = employeeRequestExtractor.extract(request, response);
        try {
            employeeService.addOrUpdate(employee);
        } catch (ValidationException e) {
            request.setAttribute("validationErrors", e.getErrorMap());
            request.setAttribute("departments", departmentService.getAll());
            request.getRequestDispatcher(FORWARD_EDIT_EMPLOYEE_PAGE).forward(request, response);
        }
        response.sendRedirect(REDIRECT_TO_EMPLOYEES_LIST + employee.getDepartment().getId());
    }
}