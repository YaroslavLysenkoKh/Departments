package command.impl.departments;

import command.Command;
import service.departments.DepartmentService;
import service.departments.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteDepartmentCommand implements Command {

    private DepartmentService departmentService;

    public DeleteDepartmentCommand() {
        this.departmentService = new DepartmentServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("departmentId"));
        departmentService.deleteById(id);
        response.sendRedirect("/");
    }

}

