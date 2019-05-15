package dao.departments;

import dao.GenericDao;
import entity.Department;

public interface DepartmentsDao extends GenericDao<Department> {
    Department getByName(String name);
}
