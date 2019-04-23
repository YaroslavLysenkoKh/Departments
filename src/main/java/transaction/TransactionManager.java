package transaction;

public interface TransactionManager {

    <T> T doInTransaction(TransactionOperation<T> operation) ;
}
