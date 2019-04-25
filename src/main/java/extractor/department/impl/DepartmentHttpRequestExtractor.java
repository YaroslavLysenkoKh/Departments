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
        department.setId(Long.parseLong(request.getParameter("departmentId")));
        department.setName(request.getParameter("name"));
        return department;
    }
}
