package command.impl.departments;

import command.Command;
import service.departments.DepartmentService;
import service.departments.impl.DepartmentServiceImpl;
import service.employee.EmployeeService;
import service.employee.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteDepartmentCommand implements Command {

    private DepartmentService departmentService;
    private EmployeeService employeeService;

    public DeleteDepartmentCommand() {
        this.departmentService = new DepartmentServiceImpl();
        this.employeeService = new EmployeeServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("departmentId"));

        if (employeeService.checkEmployeeExistenceByDepartmentId(id)) {
            request.setAttribute("errorMessage", "cannot delete department, department has employees");
            request.setAttribute("departments", departmentService.getAll());
            request.getRequestDispatcher("/").forward(request, response);
        } else {
            departmentService.deleteById(id);
            response.sendRedirect("/");
        }

    }
}
