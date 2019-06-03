package comm.exception;

public class IdException extends Exception {

    private static final String NOT_NULL = "ID CANNOT BE NULL";

    public IdException() {
        super(NOT_NULL);
    }

}
