package com.command.impl.employees;

import com.command.Command;
import com.service.employee.EmployeeService;
import com.service.impl.EmployeeHiberServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEmployeeCommand implements Command {

    private EmployeeService employeeService;

    public DeleteEmployeeCommand() {
        this.employeeService = new EmployeeHiberServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long employeeId = Long.parseLong(request.getParameter("employeeId"));
        Long departmentId = Long.parseLong(request.getParameter("departmentId"));

        employeeService.deleteById(employeeId);

        response.sendRedirect(REDIRECT_TO_EMPLOYEES_LIST + departmentId);
    }
}
