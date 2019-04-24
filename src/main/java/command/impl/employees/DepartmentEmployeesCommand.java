package command.impl.employees;

import command.Command;
import entity.Employee;
import service.employee.EmployeeService;
import service.employee.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DepartmentEmployeesCommand implements Command {

    private static final String FORWARD_EMPLOYEES_PAGE = "/WEB-INF/jsp/employees/employeesList.jsp";
    private EmployeeService employeeService;

    public DepartmentEmployeesCommand() {
        this.employeeService = new EmployeeServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("departmentId"));
        List<Employee> employeeList = null;

        if (id == null) {
            employeeList = employeeService.getAll();
        } else {
            employeeList = employeeService.getAllByDepartmentId(id);
        }

        request.setAttribute("employees", employeeList);

        request.getRequestDispatcher(FORWARD_EMPLOYEES_PAGE).forward(request, response);

    }
}
