package command.impl.employees;

import command.Command;
import service.employee.EmployeeService;
import service.employee.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEmployeeCommand implements Command {
    public static String REDIRECT_TO_EMPLOYEES_LIST = "/WEB-INF/jsp/employees/employeesList.jsp";

    private EmployeeService employeeService;

    public DeleteEmployeeCommand() {
        this.employeeService = new EmployeeServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("employeeId"));

        employeeService.deleteById(id);
        request.setAttribute("employees", employeeService.getAll());

        response.sendRedirect(request.getContextPath() + REDIRECT_TO_EMPLOYEES_LIST);
    }
}
