package com.extractor.department;

import com.entity.Department;
import com.extractor.RequestExtractor;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DepartmentHttpRequestExtractor implements RequestExtractor<Department> {
    @Override
    public Department extract(HttpServletRequest request, HttpServletResponse response) {
        Department department = new Department();
        String departmentId = request.getParameter("departmentId");
        if (!StringUtils.isBlank(departmentId))
            department.setId(Long.parseLong(departmentId));
        department.setName(request.getParameter("name"));
        return department;
    }
}
