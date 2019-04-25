package dao.departments;

import dao.GenericDao;
import entity.Department;

import java.sql.Connection;
import java.sql.SQLException;


public interface DepartmentsDao extends GenericDao<Department> {
    Department getByName(String name, Connection connection) throws SQLException;
}
