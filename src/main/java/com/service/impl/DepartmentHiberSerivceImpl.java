package com.service.impl;

import com.dao.departments.DepartmentsDao;
import com.dao.impl.DepartmentHiberDaoImpl;
import com.entity.Department;
import com.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.departments.DepartmentService;
import com.util.oval.CustomValidator;
import com.util.oval.impl.CustomValidatorImpl;

import java.util.List;

@Service
public class DepartmentHiberSerivceImpl implements DepartmentService {
    @Autowired
    private DepartmentsDao departmentGenericDao;
    private CustomValidator validator;

    public DepartmentHiberSerivceImpl() {
        this.departmentGenericDao = new DepartmentHiberDaoImpl();
        this.validator = new CustomValidatorImpl();
    }

    @Override
    public List<Department> getAll() {
        return departmentGenericDao.getAll();
    }

    @Override
    public Department getById(Long id) {
        return departmentGenericDao.getById(id);
    }

    @Override
    public Department getByName(String name) {
        return departmentGenericDao.getByName(name);
    }

    @Override
    public void deleteById(Long id) {
        Department department = getById(id);
        departmentGenericDao.delete(department);
    }

    @Override
    public boolean checkDepartmentExistenceByName(Department department) {
        List<Department> tmpDepartments = getAll();
        for (Department department1 : tmpDepartments) {
            if (department.getId() != null) {
                return department1.getName().equals(department.getName()) && department1.getId() == department.getId() ? false : true;
            } else {
                return department1.getName().equals(department.getName()) ? false : true;
            }
        }
        return false;
    }

    @Override
    public void addOrUpdate(Department department) throws ValidationException {
        validator.validate(department);
        departmentGenericDao.addOrUpdate(department);
    }
}
