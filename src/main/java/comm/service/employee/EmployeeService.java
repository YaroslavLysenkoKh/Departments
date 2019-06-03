package comm.service.employee;

import comm.entity.Employee;
import comm.exception.IdException;
import comm.exception.ValidationException;

import java.util.List;

public interface EmployeeService {

    Employee getById(Long id);

    void deleteById(Long id) throws IdException;

    List<Employee> getAllByDepartmentId(Long id) throws IdException;

    boolean checkEmployeeExistenceByEmail(Employee employee);

    Employee getEmployeeByEmail(String email);

    void addOrUpdate(Employee employee, Long departmentId) throws ValidationException, IdException;

}
