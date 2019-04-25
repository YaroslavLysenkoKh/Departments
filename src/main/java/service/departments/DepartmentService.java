package service.departments;

import entity.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAll();

    boolean add(Department department);

    Department getById(Long id);

    Department getByName(String name);

    boolean deleteById(Long id);

    boolean update(Department department);

    boolean checkDepartmentExistenceByName(String name);

}
