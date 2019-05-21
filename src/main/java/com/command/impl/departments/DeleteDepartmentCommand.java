package com.command.impl.departments;

import com.command.Command;
import com.service.departments.DepartmentService;
import com.service.impl.DepartmentHiberSerivceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteDepartmentCommand implements Command {

    private DepartmentService departmentService;

    public DeleteDepartmentCommand() {
        this.departmentService = new DepartmentHiberSerivceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("departmentId"));
        departmentService.deleteById(id);
        response.sendRedirect("/");
    }

}

