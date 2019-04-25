package command.impl.employees;

import command.Command;
import entity.Employee;
import extractor.employee.EmployeeRequestExtractor;
import extractor.employee.impl.EmployeeHttpRequestExtractor;
import service.employee.EmployeeService;
import service.employee.impl.EmployeeServiceImpl;
import validator.employee.EmployeeValidator;
import validator.employee.impl.EmployeeValidatorImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAddOrEditCommand implements Command {
    private static final String TO_EDIT_EMPLOYEE_PAGE = "/WEB-INF/jsp/employees/EditEmployee.jsp";
    private static final String TO_EMPLOYEES_PAGE = "/WEB-INF/jsp/employees/employeesList.jsp";


    private EmployeeRequestExtractor employeeRequestExtractor;
    private EmployeeService employeeService;
    private EmployeeValidator employeeValidator;

    public EmployeeAddOrEditCommand() {
        this.employeeRequestExtractor = new EmployeeHttpRequestExtractor();
        this.employeeService = new EmployeeServiceImpl();
        this.employeeValidator = new EmployeeValidatorImpl(employeeService);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<String> errors = new ArrayList<>();
        Employee employee = employeeRequestExtractor.extract(request);
        errors.addAll(employeeValidator.validate(employee));
        if (errors.isEmpty()) {
            finish(request, response, employee, employeeService);
        } else {
            backToPage(request, response, errors);
        }
    }

    private void finish(HttpServletRequest request, HttpServletResponse response, Employee employee, EmployeeService employeeService) throws IOException {
        if (employee.getId() == 0) {
            employeeService.add(employee);
        } else {
            employeeService.update(employee);
        }
        request.setAttribute("employees", employeeService.getAll());
        response.sendRedirect(request.getContextPath() + TO_EMPLOYEES_PAGE);
    }

    private void backToPage(HttpServletRequest request, HttpServletResponse response, List<String> errors) throws
            IOException, ServletException {

        request.setAttribute("validationErrors", errors);
        request.setAttribute("employeeId", request.getParameter("employeeId"));
        request.setAttribute("email", request.getParameter("email"));
        request.setAttribute("password", request.getParameter("password"));
        request.setAttribute("salary", request.getParameter("salary"));
        request.setAttribute("birthDate", request.getParameter("birthDate"));
        request.getRequestDispatcher(TO_EDIT_EMPLOYEE_PAGE).forward(request, response);
    }
}
