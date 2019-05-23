package comm.command.impl.departments;

import comm.command.Command;
import comm.entity.Department;
import comm.exception.ValidationException;
import comm.extractor.RequestExtractor;
import comm.service.departments.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("/addDepartment")
public class DepartmentAddOrEditCommand implements Command {
    private DepartmentService departmentService;
    private RequestExtractor<Department> departmentRequestExtractor;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    public void setDepartmentRequestExtractor(RequestExtractor<Department> departmentRequestExtractor) {
        this.departmentRequestExtractor = departmentRequestExtractor;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Department department = departmentRequestExtractor.extract(request, response);
        try {
            departmentService.addOrUpdate(department);
        } catch (ValidationException e) {
            request.setAttribute("validationErrors", e.getErrorMap());
            request.getRequestDispatcher(FORWARD_EDIT_DEPARTMENT_PAGE).forward(request, response);
        }
        response.sendRedirect("/");
    }
}
