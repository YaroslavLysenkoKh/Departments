package service.employee;

import entity.Employee;
import exception.ValidationException;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();

    Employee getById(Long id);

    boolean deleteById(Long id);

    List<Employee> getAllByDepartmentId(Long id);

    boolean checkEmployeeExistenceByEmail(String email);

    boolean checkEmployeeExistenceByDepartmentId(Long id);

    Employee getEmployeeByEmail(String email);

    boolean addOrUpdate(Employee employee) throws ValidationException;

}
