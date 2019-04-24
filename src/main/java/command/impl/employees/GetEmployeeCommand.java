package command.impl.employees;

import command.Command;
import service.departments.DepartmentService;
import service.departments.impl.DepartmentServiceImpl;
import service.employee.EmployeeService;
import service.employee.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetEmployeeCommand implements Command {
    private static final String FORWARD_EDIT_EMPLOYEE_PAGE = "/WEB-INF/jsp/employees/EditEmployee.jsp";
    private DepartmentService departmentService;
    private EmployeeService employeeService;

    public GetEmployeeCommand() {
        this.departmentService = new DepartmentServiceImpl();
        this.employeeService = new EmployeeServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Long id = Long.parseLong(request.getParameter("employeeId"));
        if (id == 0) {
            request.setAttribute("departments", departmentService.getAll());
        } else {
            request.setAttribute("employee", employeeService.getById(id));
            request.setAttribute("departments", departmentService.getAll());
        }


        request.getRequestDispatcher(FORWARD_EDIT_EMPLOYEE_PAGE).forward(request, response);
    }
}
