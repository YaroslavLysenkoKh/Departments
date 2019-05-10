package command.impl.departments;

import command.Command;
import service.departments.DepartmentService;
import service.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetDepartmentCommand implements Command {
    private DepartmentService departmentService;

    public GetDepartmentCommand() {
        this.departmentService = new DepartmentServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String depId = request.getParameter("departmentId");
        if (depId != null && !depId.isEmpty()) {
            request.setAttribute("department", departmentService.getById(Long.parseLong(depId)));
        }
        request.getRequestDispatcher(FORWARD_EDIT_DEPARTMENT_PAGE).forward(request, response);
    }
}
