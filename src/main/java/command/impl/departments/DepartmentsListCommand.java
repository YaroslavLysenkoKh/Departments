package command.impl.departments;

import command.Command;
import service.departments.DepartmentService;
import service.impl.DepartmentHiberSerivceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DepartmentsListCommand implements Command {

    private DepartmentService departmentService;

    public DepartmentsListCommand() {
        this.departmentService = new DepartmentHiberSerivceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("departments", departmentService.getAll());
        request.getRequestDispatcher(FORWARD_DEPARTMENTS_PAGE).forward(request, response);
    }
}

