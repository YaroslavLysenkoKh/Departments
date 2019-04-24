package dao.departments.impl;

import dao.departments.DepartmentsDao;
import entity.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentsDaoImpl implements DepartmentsDao {
    private static final String SELECT_ALL_DEPARTMENTS = "SELECT * FROM departments";
    private static final String INSERT_DEPARTMENT = "INSERT INTO departments VALUES (DEFAULT, ?)";
    private static final String UPDATE_DEPARTMENT = "UPDATE departments SET name=? WHERE id=?";
    private static final String SELECT_DEPARTMENT_BY_ID = "SELECT * FROM departments WHERE id=?";
    private static final String DELETE_DEPARTMENT_BY_ID = "DELETE FROM departments WHERE id=?";

    @Override
    public void add(Department department, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEPARTMENT)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Department> getAll(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_DEPARTMENTS)) {
            List<Department> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(extractDepartment(resultSet));
            }
            return result;
        }
    }

    @Override
    public void update(Department department, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEPARTMENT)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Department getById(Long id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEPARTMENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return extractDepartment(resultSet);
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
        return department;
    }
}
