package com.util.oval.department;

import com.entity.Department;
import net.sf.oval.constraint.CheckWithCheck;
import com.service.departments.DepartmentService;
import com.service.impl.DepartmentHiberSerivceImpl;


public class NameCheck implements CheckWithCheck.SimpleCheck {
    private DepartmentService departmentService;

    public NameCheck() {
        this.departmentService = new DepartmentHiberSerivceImpl();
    }

    @Override
    public boolean isSatisfied(Object validatedObject, Object value) {
        Department department = (Department) validatedObject;
        if (department.getName().isEmpty())
            return false;
        if (departmentService.checkDepartmentExistenceByName(department))
            return false;
        return true;
    }
}
