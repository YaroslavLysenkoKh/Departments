package transaction.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import transaction.TransactionManager;
import transaction.TransactionOperation;
import util.hibernate.HibernateUtil;


public class HiberTransactionManagerImpl implements TransactionManager {

    @Override
    public <T> T doInTransaction(TransactionOperation<T> operation) {
        T result = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            result = operation.execute(HibernateUtil.getSessionFactory().openSession());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }
}
