package command.impl.employees;

import command.Command;
import service.departments.DepartmentService;
import service.employee.EmployeeService;
import service.impl.DepartmentHiberSerivceImpl;
import service.impl.EmployeeHiberServiceImpl;

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
