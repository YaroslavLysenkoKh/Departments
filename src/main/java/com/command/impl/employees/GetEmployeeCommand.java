package com.command.impl.employees;

import com.command.Command;
import com.service.departments.DepartmentService;
import com.service.employee.EmployeeService;
import com.service.impl.DepartmentHiberSerivceImpl;
import com.service.impl.EmployeeHiberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetEmployeeCommand implements Command {
    private DepartmentService departmentService;
    private EmployeeService employeeService;

    public GetEmployeeCommand() {
        this.departmentService = new DepartmentHiberSerivceImpl();
        this.employeeService = new EmployeeHiberServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String employeeId = request.getParameter("employeeId");
        String departmentId = request.getParameter("departmentId");
        if (employeeId != null && !employeeId.isEmpty()) {
            request.setAttribute("employee", employeeService.getById(Long.parseLong(employeeId)));
        }
        request.setAttribute("departmentId", departmentId);
        request.setAttribute("departments", departmentService.getAll());
        request.getRequestDispatcher(FORWARD_EDIT_EMPLOYEE_PAGE).forward(request, response);
    }
}
