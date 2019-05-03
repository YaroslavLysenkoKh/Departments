package service.departments;

import entity.Department;
import exception.ValidationException;

import java.util.List;

public interface DepartmentService {

    List<Department> getAll();

    boolean add(Department department) throws ValidationException;

    Department getById(Long id);

    Department getByName(String name);

    boolean deleteById(Long id);

    boolean update(Department department) throws ValidationException;

    boolean checkDepartmentExistenceByName(String name);

}
