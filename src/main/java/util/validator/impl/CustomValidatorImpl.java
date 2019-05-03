package util.validator.impl;

import exception.ValidationException;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;
import util.validator.CustomValidator;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomValidatorImpl implements CustomValidator {

    private Validator validator;
    private Map<String, String> errorMap;


    public CustomValidatorImpl() {
        this.validator = new Validator();
        this.errorMap = new HashMap<>();
    }

    public void validate(Object validateObject) throws ValidationException {
        List<ConstraintViolation> violationList = validator.validate(validateObject);
        if (!violationList.isEmpty()) {
            for (ConstraintViolation violation : violationList) {
                String value = violation.getMessage();
                OValContext context = violation.getContext();
                if (context instanceof FieldContext) {
                    Field field = ((FieldContext) context).getField();
                    String key = field.getName();
                    errorMap.put(key, value);
                }
            }
            throw new ValidationException(errorMap);
        }

    }
}
