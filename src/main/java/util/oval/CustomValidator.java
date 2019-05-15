package util.oval;

import exception.ValidationException;

public interface CustomValidator {

    void validate(Object validateObject) throws ValidationException;
}
