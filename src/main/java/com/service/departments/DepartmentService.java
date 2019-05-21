package com.service.departments;

import com.entity.Department;
import com.exception.ValidationException;

import java.util.List;

public interface DepartmentService {

    List<Department> getAll();

    Department getById(Long id);

    Department getByName(String name);

    void deleteById(Long id);

    boolean checkDepartmentExistenceByName(Department department);

    void addOrUpdate(Department department) throws ValidationException;

}
