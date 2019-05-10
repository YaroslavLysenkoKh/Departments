package dao.employees.impl;

import dao.employees.EmployeesDao;
import entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDaoImpl implements EmployeesDao {

    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employees";
    private static final String SELECT_ALL_EMPLOYEES_BY_DEPARTMENT_ID = "SELECT * FROM employees WHERE id_department = ?";
    private static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE id = ?";
    private static final String SELECT_EMPLOYEE_BY_EMAIL = "SELECT * FROM employees WHERE email = ?";
    private static final String DELETE_EMPLOYEE_BY_ID = "DELETE FROM employees WHERE id = ?";
    private static final String INSERT_EMPLOYEE = "INSERT INTO employees VALUES (DEFAULT,?,?,?,?)";
    private static final String UPDATE_EMPLOYEE = "UPDATE employees SET email=?,salary=?,birth_date=?,id_department=? WHERE id=?";

    @Override
    public void addOrUpdate(Employee employee, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(employee.getId() == null ? INSERT_EMPLOYEE : UPDATE_EMPLOYEE)) {
            preparedStatement.setString(1, employee.getEmail());
            preparedStatement.setInt(2, employee.getSalary());
            preparedStatement.setDate(3, new Date(employee.getBirthDate().getTime()));
            preparedStatement.setLong(4, employee.getDepartmentId());
            if (employee.getId() != null) {
                preparedStatement.setLong(5, employee.getId());
            }
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Employee> getAll(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_EMPLOYEES)) {
            List<Employee> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(extractEmployee(resultSet));
            }
            return result;
        }
    }

    private Employee extractEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getLong(1));
        employee.setEmail(resultSet.getString(2));
        employee.setSalary(resultSet.getInt(3));
        employee.setBirthDate(resultSet.getDate(4));
        employee.setDepartmentId(resultSet.getLong(5));
        return employee;
    }

    @Override
    public Employee getById(Long id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return extractEmployee(resultSet);
            return null;
        }
    }

    @Override
    public void deleteById(Long id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Employee> getAllByDepartmentId(Long id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES_BY_DEPARTMENT_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Employee> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(extractEmployee(resultSet));
            }
            return result;
        }
    }

    @Override
    public Employee getEmployeeByEmail(String email, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return extractEmployee(resultSet);
            return null;
        }
    }
}