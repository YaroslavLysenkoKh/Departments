package comm.service.departments;

import comm.entity.Department;
import comm.exception.IdException;
import comm.exception.ValidationException;

import java.util.List;

public interface DepartmentService {

    List<Department> getAll();

    Department getById(Long id) throws IdException;

    Department getByName(String name);

    void deleteById(Long id) throws IdException;

    boolean checkDepartmentExistenceByName(Department department);

    void addOrUpdate(Department department) throws ValidationException;

}
