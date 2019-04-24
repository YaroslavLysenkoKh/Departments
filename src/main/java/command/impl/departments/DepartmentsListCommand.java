package command.impl.departments;

import command.Command;
import entity.Department;
import service.departments.DepartmentService;
import service.departments.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DepartmentsListCommand implements Command {
    private static final String FORWARD_DEPARTMENTS_PAGE = "/WEB-INF/jsp/departmentsList.jsp";

    private DepartmentService departmentService;

    public DepartmentsListCommand() {
        this.departmentService = new DepartmentServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Department> departmentList = departmentService.getAll();

        request.setAttribute("departments", departmentList);

        request.getRequestDispatcher(FORWARD_DEPARTMENTS_PAGE).forward(request, response);
    }
}
