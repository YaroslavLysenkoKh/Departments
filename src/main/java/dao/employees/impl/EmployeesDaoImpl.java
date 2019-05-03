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
    private static final String INSERT_EMPLOYEE = "INSERT INTO employees VALUES (DEFAULT,?,?,?,?,?)";
    private static final String UPDATE_EMPLOYEE = "UPDATE employees SET email=?,password=?,salary=?,birth_date=?,id_department=? WHERE id=?";

    @Override
    public void add(Employee employee, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE)) {
            preparedStatement.setString(1, employee.getEmail());
            preparedStatement.setString(2, employee.getPassword());
            preparedStatement.setInt(3, employee.getSalary());
            preparedStatement.setDate(4, (Date) employee.getBirthDate());
            preparedStatement.setLong(5, employee.getDepartmentId());
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
        employee.setSalary(resultSet.getInt(4));
        employee.setBirthDate(resultSet.getDate(5));
        employee.setDepartmentId(resultSet.getLong(6));
        return employee;
    }


    @Override
    public void update(Employee employee, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE)) {
            preparedStatement.setString(1, employee.getEmail());
            preparedStatement.setString(2, employee.getPassword());
            preparedStatement.setInt(3, employee.getSalary());
            preparedStatement.setDate(4, (Date) employee.getBirthDate());
            preparedStatement.setLong(5, employee.getDepartmentId());
            preparedStatement.setLong(6, employee.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Employee getById(Long id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return extractEmployee(resultSet);
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
            preparedStatement.setNString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.next())
                return extractEmployee(resultSet);
            return null;

        }
    }
}