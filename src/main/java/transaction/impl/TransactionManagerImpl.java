package transaction.impl;

import exception.DbException;
import transaction.TransactionManager;
import transaction.TransactionOperation;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManagerImpl implements TransactionManager {
    private final DataSource dataSource;

    public TransactionManagerImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public <T> T doInTransaction(TransactionOperation<T> operation) {
        T result;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            result = operation.execute(dataSource.getConnection());
            connection.commit();
        } catch (SQLException TransactionException) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollBackException) {
                  //NOP
                }
            }
            throw new DbException(TransactionException);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException closeException) {
                  //NOP
                }
            }
        }
        return result;
    }
}





