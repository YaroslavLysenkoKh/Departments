package service.employee;

import entity.Employee;
import exception.ValidationException;

import java.util.List;

public interface EmployeeService {

    Employee getById(Long id);

    void deleteById(Long id);

    List<Employee> getAllByDepartmentId(Long id);

    boolean checkEmployeeExistenceByEmail(Employee employee);

    Employee getEmployeeByEmail(String email);

    void addOrUpdate(Employee employee) throws ValidationException;

}
