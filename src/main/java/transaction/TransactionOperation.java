package transaction;

import org.hibernate.Session;


public interface TransactionOperation<T> {
    T execute(Session connection);
}
