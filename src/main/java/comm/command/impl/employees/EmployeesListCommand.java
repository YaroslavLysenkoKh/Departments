package comm.command.impl.employees;

import comm.command.Command;
import comm.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("/departmentEmployees")
public class EmployeesListCommand implements Command {

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("departmentId"));

        request.setAttribute("employees", employeeService.getAllByDepartmentId(id));

        request.setAttribute("departmentId", id);
        request.getRequestDispatcher(FORWARD_EMPLOYEES_PAGE).forward(request, response);

    }
}
