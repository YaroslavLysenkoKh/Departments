package dao.employees;

import dao.GenericDao;
import entity.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EmployeesDao extends GenericDao<Employee> {

    List<Employee> getAllByDepartmentId(Long id, Connection connection) throws SQLException;

    Employee getEmployeeByEmail(String email, Connection connection) throws SQLException;

}
