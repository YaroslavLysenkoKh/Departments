package service.departments;

import entity.Department;
import exception.ValidationException;

import java.util.List;

public interface DepartmentService {

    List<Department> getAll();

    Department getById(Long id);

    Department getByName(String name);

    boolean deleteById(Long id);

    boolean checkDepartmentExistenceByName(String name);

    boolean addOrUpdate(Department department) throws ValidationException;

}
