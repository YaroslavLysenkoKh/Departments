package dao.employees;

import dao.GenericDao;
import entity.Employee;

import java.util.List;

public interface EmployeesDao extends GenericDao<Employee> {

    List<Employee> getAllByDepartmentId(Long id);

    Employee getEmployeeByEmail(String email);

}
