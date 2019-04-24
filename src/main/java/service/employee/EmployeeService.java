package service.employee;

import entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();

    boolean add(Employee employee);

    Employee getById(Long id);

    boolean deleteById(Long id);

    boolean update(Employee employee);

    List<Employee> getAllByDepartmentId(Long id);

    boolean checkEmployeeExistenceByEmail(String email);

    Employee getEmployeeByEmail(String email);

}
