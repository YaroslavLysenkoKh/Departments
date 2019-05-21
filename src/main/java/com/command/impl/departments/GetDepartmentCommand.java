package com.command.impl.departments;

import com.command.Command;
import org.springframework.stereotype.Component;
import com.service.departments.DepartmentService;
import com.service.impl.DepartmentHiberSerivceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GetDepartmentCommand implements Command {
    private DepartmentService departmentService;

    public GetDepartmentCommand() {
        this.departmentService = new DepartmentHiberSerivceImpl();
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
