package dao;

import entity.Department;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {
    void add(T model, Connection connection) throws SQLException;

    List<T> getAll(Connection connection) throws SQLException;

    void update(T model, Connection connection) throws SQLException;

    T getById(Long id, Connection connection) throws SQLException;

    void deleteById(Long id, Connection connection) throws SQLException;
}
