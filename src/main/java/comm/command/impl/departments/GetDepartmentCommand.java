package comm.command.impl.departments;

import comm.command.Command;
import comm.service.departments.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("/getToEditDepartment")
public class GetDepartmentCommand implements Command {

    private DepartmentService departmentService;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
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
