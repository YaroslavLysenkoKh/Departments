package dao.impl;

import dao.departments.DepartmentsDao;
import entity.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentsDaoImpl implements DepartmentsDao {
    private static final String SELECT_ALL_DEPARTMENTS_OUTER_JOIN = "select d.id, d.name, ifnull(count(e.id), 0) as 'employees number' from employees e " +
            "right outer join departments d on e.id_department= d.id group by d.id";
    private static final String INSERT_DEPARTMENT = "INSERT INTO departments VALUES (DEFAULT, ?)";
    private static final String UPDATE_DEPARTMENT = "UPDATE departments SET name=? WHERE id=?";
    private static final String SELECT_DEPARTMENT_BY_ID = "select d.id, d.name, ifnull(count(e.id), 0) as 'employees number' from employees e " +
            "right outer join departments d on e.id_department= d.id where d.id =?";
    private static final String SELECT_DEPARTMENT_BY_NAME = "SELECT * FROM departments WHERE name=?";
    private static final String DELETE_DEPARTMENT_BY_ID = "DELETE FROM departments WHERE id=?";

    @Override
    public void addOrUpdate(Department department, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(department.getId() == null ? INSERT_DEPARTMENT : UPDATE_DEPARTMENT)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Department> getAll(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_DEPARTMENTS_OUTER_JOIN)) {
            List<Department> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(extractDepartment(resultSet));
            }
            return result;
        }
    }

    @Override
    public Department getById(Long id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEPARTMENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return extractDepartment(resultSet);
            return null;
        }
    }

    @Override
    public void deleteById(Long id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DEPARTMENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    private Department extractDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getLong(1));
        department.setName(resultSet.getString(2));
        department.setCount(resultSet.getInt(3));
        return department;
    }

    @Override
    public Department getByName(String name, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEPARTMENT_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return extractDepartment(resultSet);
            return null;
        }
    }
}
