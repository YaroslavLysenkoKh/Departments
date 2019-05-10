package service.departments;

import entity.Department;
import exception.ValidationException;

import java.util.List;

public interface DepartmentService {

    List<Department> getAll();

    Department getById(Long id);

    boolean deleteById(Long id);

    boolean checkDepartmentExistenceByName(Department department);

    boolean addOrUpdate(Department department) throws ValidationException;

}
