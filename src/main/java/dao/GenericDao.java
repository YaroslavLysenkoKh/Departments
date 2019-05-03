package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {

    List<T> getAll(Connection connection) throws SQLException;

    T getById(Long id, Connection connection) throws SQLException;

    void deleteById(Long id, Connection connection) throws SQLException;

    void addOrUpdate(T model, Connection connection) throws SQLException;
}
