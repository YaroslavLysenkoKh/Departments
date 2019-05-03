package command.impl.departments;

import command.Command;
import entity.Department;
import exception.ValidationException;
import extractor.department.DepartmentRequestExtractor;
import extractor.department.impl.DepartmentHttpRequestExtractor;
import service.departments.DepartmentService;
import service.departments.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DepartmentAddOrEditCommand implements Command {
    private DepartmentService departmentService;
    private DepartmentRequestExtractor departmentRequestExtractor;

    public DepartmentAddOrEditCommand() {
        this.departmentService = new DepartmentServiceImpl();
        this.departmentRequestExtractor = new DepartmentHttpRequestExtractor();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Department department = departmentRequestExtractor.extract(request);
        finish(request, response, department, departmentService);
    }

    private void finish(HttpServletRequest request, HttpServletResponse response, Department department, DepartmentService departmentService) throws IOException, ServletException {
        try {
            departmentService.addOrUpdate(department);
        } catch (ValidationException e) {
            request.setAttribute("validationErrors", e.getErrorMap());
            request.setAttribute("department", department);
            request.getRequestDispatcher(FORWARD_EDIT_DEPARTMENT_PAGE).forward(request, response);
        }
        response.sendRedirect("/");
    }

}
