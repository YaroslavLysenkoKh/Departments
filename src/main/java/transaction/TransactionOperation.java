package transaction;

import java.sql.Connection;
import java.sql.SQLException;

public interface TransactionOperation<T> {
    T execute(Connection connection) throws SQLException;
}
