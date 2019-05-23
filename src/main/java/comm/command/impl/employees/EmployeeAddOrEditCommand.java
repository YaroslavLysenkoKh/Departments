package comm.command.impl.employees;

import comm.command.Command;
import comm.entity.Employee;
import comm.exception.ValidationException;
import comm.extractor.RequestExtractor;
import comm.service.departments.DepartmentService;
import comm.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("/addEmployee")
public class EmployeeAddOrEditCommand implements Command {

    private RequestExtractor<Employee> employeeRequestExtractor;
    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Autowired
    public void setEmployeeRequestExtractor(RequestExtractor<Employee> employeeRequestExtractor) {
        this.employeeRequestExtractor = employeeRequestExtractor;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
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