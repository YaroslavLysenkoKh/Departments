package transaction.impl;

import dao.DbManager;
import exception.DbException;
import transaction.TransactionManager;
import transaction.TransactionOperation;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManagerImpl implements TransactionManager {
    private final DbManager dbManager;

    public TransactionManagerImpl() {

        this.dbManager = DbManager.getInstance();
    }

    @Override
    public <T> T doInTransaction(TransactionOperation<T> operation) {
        T result;
        Connection connection = null;
        try {
            connection = dbManager.getConnection();
            connection.setAutoCommit(false);
            result = operation.execute(dbManager.getConnection());
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





