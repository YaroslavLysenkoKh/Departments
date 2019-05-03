package exception;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends Exception {

    private Map<String, String> errorMap = new HashMap<>();

    public ValidationException(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

}
