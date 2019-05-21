package com.command.impl.departments;

import com.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.service.departments.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller("/")
public class DepartmentsListCommand implements Command {

    @Autowired
    private DepartmentService departmentService;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("departments", departmentService.getAll());
        request.getRequestDispatcher(FORWARD_DEPARTMENTS_PAGE).forward(request, response);
    }
}
