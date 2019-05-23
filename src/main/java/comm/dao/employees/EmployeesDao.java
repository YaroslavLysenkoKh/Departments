package comm.dao.employees;

import comm.dao.GenericDao;
import comm.entity.Employee;

import java.util.List;

public interface EmployeesDao extends GenericDao<Employee> {

    List<Employee> getAllByDepartmentId(Long id);

    Employee getEmployeeByEmail(String email);

}
