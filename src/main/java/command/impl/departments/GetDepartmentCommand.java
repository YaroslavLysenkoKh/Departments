package command.impl.departments;

import command.Command;
import service.departments.DepartmentService;
import service.departments.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetDepartmentCommand implements Command {
    private static final String FORWARD_EDIT_DEPARTMENT_PAGE = "/WEB-INF/jsp/departments/EditDepartment.jsp";

    private DepartmentService departmentService;

    public GetDepartmentCommand() {
        this.departmentService = new DepartmentServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("action").equals("update")) {
            request.setAttribute("department", departmentService.getById(Long.parseLong(request.getParameter("departmentId"))));
        }
        request.getRequestDispatcher(FORWARD_EDIT_DEPARTMENT_PAGE).forward(request, response);
    }
}
