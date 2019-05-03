package command.impl.employees;

import command.Command;
import entity.Employee;
import exception.ValidationException;
import extractor.employee.EmployeeRequestExtractor;
import extractor.employee.impl.EmployeeHttpRequestExtractor;
import service.departments.DepartmentService;
import service.departments.impl.DepartmentServiceImpl;
import service.employee.EmployeeService;
import service.employee.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeAddOrEditCommand implements Command {
    private EmployeeRequestExtractor employeeRequestExtractor;
    private EmployeeService employeeService;
    private DepartmentService departmentService;

    public EmployeeAddOrEditCommand() {
        this.employeeRequestExtractor = new EmployeeHttpRequestExtractor();
        this.employeeService = new EmployeeServiceImpl();
        this.departmentService = new DepartmentServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Employee employee = employeeRequestExtractor.extract(request);
        try {
            employeeService.addOrUpdate(employee);
        } catch (ValidationException e) {
            request.setAttribute("validationErrors", e.getErrorMap());
            request.setAttribute("employee", employee);
            request.setAttribute("departments", departmentService.getAll());
            request.getRequestDispatcher(FORWARD_EDIT_EMPLOYEE_PAGE).forward(request, response);
        }
        response.sendRedirect(REDIRECT_TO_EMPLOYEES_LIST + employee.getDepartmentId());
    }
}
