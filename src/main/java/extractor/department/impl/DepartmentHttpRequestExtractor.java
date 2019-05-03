package extractor.department.impl;

import entity.Department;
import extractor.department.DepartmentRequestExtractor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DepartmentHttpRequestExtractor implements DepartmentRequestExtractor {
    @Override
    public Department extract(HttpServletRequest request) throws IOException, ServletException {
        Department department = new Department();
        String departmentId = request.getParameter("departmentId");
        if (departmentId.length() > 0)
            department.setId(Long.parseLong(departmentId));
        department.setName(request.getParameter("name"));
        return department;
    }
}
