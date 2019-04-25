package command.impl.departments;

import command.Command;
import entity.Department;
import extractor.department.DepartmentRequestExtractor;
import extractor.department.impl.DepartmentHttpRequestExtractor;
import service.departments.DepartmentService;
import service.departments.impl.DepartmentServiceImpl;
import validator.department.DepartmentValidator;
import validator.department.impl.DepartmentValidatorImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentAddorEditCommand implements Command {
    private static final String TO_DEPARTMENTS = "/WEB-INF/jsp/departments/departmentsList.jsp";
    private static final String TO_EDIT_DEPARTMENT_PAGE = "/WEB-INF/jsp/departments/EditDepartment.jsp";
    private DepartmentService departmentService;
    private DepartmentRequestExtractor departmentRequestExtractor;
    private DepartmentValidator departmentValidator;

    public DepartmentAddorEditCommand() {
        this.departmentService = new DepartmentServiceImpl();
        this.departmentRequestExtractor = new DepartmentHttpRequestExtractor();
        this.departmentValidator = new DepartmentValidatorImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<String> errors = new ArrayList<>();
        Department department = departmentRequestExtractor.extract(request);
        errors.addAll(departmentValidator.validate(department));
        if (errors.isEmpty()) {
            finish(request, response, department, departmentService);
            departmentService.add(department);
            request.setAttribute("departments", departmentService.getAll());
            response.sendRedirect(request.getContextPath() + TO_DEPARTMENTS);
        } else {
            backToPage(request, response, errors);
        }
    }

    private void finish(HttpServletRequest request, HttpServletResponse response, Department department, DepartmentService departmentService) throws IOException {
        if (department.getId() == 0) {
            departmentService.add(department);
        } else {
            departmentService.update(department);
        }
        request.setAttribute("departments", departmentService.getAll());
        response.sendRedirect(request.getContextPath() + TO_DEPARTMENTS);
    }

    private void backToPage(HttpServletRequest request, HttpServletResponse response, List<String> errors) throws ServletException, IOException {
        request.setAttribute("validationErrors", errors);
        request.setAttribute("departmentId", request.getParameter("departmentId"));
        request.setAttribute("name", request.getParameter("name"));
        request.getRequestDispatcher(TO_EDIT_DEPARTMENT_PAGE).forward(request, response);
    }
}
