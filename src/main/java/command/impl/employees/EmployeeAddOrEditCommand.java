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
    private static final String REDIRECT_TO_EMPLOYEES_LIST = "departmentEmployees?action=departmentEmployee&departmentId=";
    private static final String TO_EDIT_EMPLOYEE_PAGE = "/WEB-INF/jsp/employees/EditEmployee.jsp";


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
        finish(request, response, employee, employeeService);
    }

    private void finish(HttpServletRequest request, HttpServletResponse response, Employee employee, EmployeeService employeeService) throws IOException, ServletException {
        try {
            if (employee.getId() == null) {
                employeeService.add(employee);
            } else {
                employeeService.update(employee);
            }
        } catch (ValidationException e) {
            backToPage(request, response, e, employee);
        }
        response.sendRedirect(REDIRECT_TO_EMPLOYEES_LIST + employee.getDepartmentId());
    }

    private void backToPage(HttpServletRequest request, HttpServletResponse response, ValidationException e, Employee employee) throws ServletException, IOException {
        request.setAttribute("validationErrors", e.getErrorMap());
        request.setAttribute("employee", employee);
        request.setAttribute("departments", departmentService.getAll());
        request.getRequestDispatcher(TO_EDIT_EMPLOYEE_PAGE).forward(request, response);
    }
}
