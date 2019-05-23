package comm.command.impl.employees;

import comm.command.Command;
import comm.service.departments.DepartmentService;
import comm.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("/getToEditEmployee")
public class GetEmployeeCommand implements Command {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    public GetEmployeeCommand() {
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
