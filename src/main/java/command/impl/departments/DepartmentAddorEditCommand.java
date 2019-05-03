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

public class DepartmentAddorEditCommand implements Command {
    private static final String TO_DEPARTMENTS = "/WEB-INF/jsp/departments/EditDepartment.jsp";
    private DepartmentService departmentService;
    private DepartmentRequestExtractor departmentRequestExtractor;

    public DepartmentAddorEditCommand() {
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
            if (department.getId() == null) {
                departmentService.add(department);
            } else {
                departmentService.update(department);
            }
        } catch (ValidationException e) {
            backToPage(request, response, e, department);
        }
        response.sendRedirect("/");
    }

    private void backToPage(HttpServletRequest request, HttpServletResponse response, ValidationException e, Department department) throws ServletException, IOException {
        request.setAttribute("validationErrors", e.getErrorMap());
        request.setAttribute("department", department);
        request.getRequestDispatcher(TO_DEPARTMENTS).forward(request, response);
    }
}
