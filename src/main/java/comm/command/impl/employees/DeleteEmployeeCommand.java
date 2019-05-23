package comm.command.impl.employees;

import comm.command.Command;
import comm.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("/deleteEmployee")
public class DeleteEmployeeCommand implements Command {


    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long employeeId = Long.parseLong(request.getParameter("employeeId"));
        Long departmentId = Long.parseLong(request.getParameter("departmentId"));

        employeeService.deleteById(employeeId);

        response.sendRedirect(REDIRECT_TO_EMPLOYEES_LIST + departmentId);
    }
}
