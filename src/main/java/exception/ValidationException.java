package exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationException extends Exception {

    private Map<String, List<String>> errorMap = new HashMap<>();

    public ValidationException(Map<String, List<String>> errorMap) {
        this.errorMap = errorMap;
    }

    public Map<String, List<String>> getErrorMap() {
        return errorMap;
    }

}
