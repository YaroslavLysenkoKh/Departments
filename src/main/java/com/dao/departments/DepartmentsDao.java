package com.dao.departments;

import com.dao.GenericDao;
import com.entity.Department;

public interface DepartmentsDao extends GenericDao<Department> {
    Department getByName(String name);
}
