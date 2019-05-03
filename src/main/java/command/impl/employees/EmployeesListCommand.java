package command.impl.employees;

import command.Command;
import service.employee.EmployeeService;
import service.employee.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeesListCommand implements Command {

    private EmployeeService employeeService;

    public EmployeesListCommand() {
        this.employeeService = new EmployeeServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("departmentId"));

        request.setAttribute("employees", employeeService.getAllByDepartmentId(id));
        request.getRequestDispatcher(FORWARD_EMPLOYEES_PAGE).forward(request, response);

    }
}
