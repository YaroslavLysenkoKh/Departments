package service.employee;

import entity.Employee;
import exception.ValidationException;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();

    boolean add(Employee employee) throws ValidationException;

    Employee getById(Long id);

    boolean deleteById(Long id);

    boolean update(Employee employee) throws ValidationException;

    List<Employee> getAllByDepartmentId(Long id);

    boolean checkEmployeeExistenceByEmail(String email);

    boolean checkEmployeeExistenceByDepartmentId(Long id);

    Employee getEmployeeByEmail(String email);

}
