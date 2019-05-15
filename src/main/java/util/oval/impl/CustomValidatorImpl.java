package util.oval.impl;

import exception.ValidationException;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;
import util.oval.CustomValidator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomValidatorImpl implements CustomValidator {

    private Validator validator;
    private Map<String, List<String>> errorMap;


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
                    if (errorMap.get(key) == null) {
                        List<String> tmpList = new ArrayList<>();
                        tmpList.add(value);
                        errorMap.put(key, tmpList);
                    } else {
                        errorMap.get(key).add(value);
                    }
                }

            }
            throw new ValidationException(errorMap);
        }

    }
}
