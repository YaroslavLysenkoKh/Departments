package com.service.impl;

import com.dao.employees.EmployeesDao;
import com.dao.impl.EmployeeHiberDaoImpl;
import com.entity.Employee;
import com.exception.ValidationException;
import com.service.employee.EmployeeService;
import com.util.oval.CustomValidator;
import com.util.oval.impl.CustomValidatorImpl;

import java.util.List;

public class EmployeeHiberServiceImpl implements EmployeeService {
    private final CustomValidator validator;
    private final EmployeesDao employeesDao;

    public EmployeeHiberServiceImpl() {
        this.validator = new CustomValidatorImpl();
        this.employeesDao = new EmployeeHiberDaoImpl();
    }

    @Override
    public Employee getById(Long id) {
        return employeesDao.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        Employee employee = getById(id);
        employeesDao.delete(employee);
    }

    @Override
    public List<Employee> getAllByDepartmentId(Long id) {
        return employeesDao.getAllByDepartmentId(id);
    }

    @Override
    public boolean checkEmployeeExistenceByEmail(Employee employee) {
        Employee tmpEmployee = getEmployeeByEmail(employee.getEmail());
        if (tmpEmployee != null) {
            if (tmpEmployee.getId() == employee.getId() && tmpEmployee.getEmail().equals(employee.getEmail())) {
                return false;
            }
            if (tmpEmployee.getEmail().equals(employee.getEmail())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return employeesDao.getEmployeeByEmail(email);
    }

    @Override
    public void addOrUpdate(Employee employee) throws ValidationException {
        validator.validate(employee);
        employeesDao.addOrUpdate(employee);
    }
}
