package com.util.oval.employee;

import com.entity.Employee;
import net.sf.oval.constraint.CheckWithCheck;
import com.service.employee.EmployeeService;
import com.service.impl.EmployeeHiberServiceImpl;

public class EmailCheck implements CheckWithCheck.SimpleCheck {
    private EmployeeService employeeService;

    public EmailCheck() {
        this.employeeService = new EmployeeHiberServiceImpl();
    }

    @Override
    public boolean isSatisfied(Object validatedObject, Object value) {
        Employee employee = (Employee) validatedObject;
        if (employee.getEmail().isEmpty())
            return false;
        if (employeeService.checkEmployeeExistenceByEmail(((Employee) validatedObject)))
            return false;
        return true;
    }
}
