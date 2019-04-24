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
    public static String TO_EDIT_EMPLOYEE_PAGE = "/WEB-INF/jsp/employees/EditEmployee.jsp";


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
            employeeService.add(employee);
            request.setAttribute("employees", employeeService.getAll());
            response.sendRedirect(request.getContextPath() + TO_EDIT_EMPLOYEE_PAGE);

        } else {
            backToPage(request, response, errors);
        }
    }

    private void backToPage(HttpServletRequest request, HttpServletResponse response, List<String> errors) throws IOException, ServletException {

        request.setAttribute("validationErrors", errors);
        request.setAttribute("email", request.getParameter("email"));
        request.setAttribute("salary", request.getParameter("salary"));
        request.getRequestDispatcher(TO_EDIT_EMPLOYEE_PAGE).forward(request, response);
    }
}
