package comm.util.oval;

import comm.exception.ValidationException;

public interface CustomValidator {

    void validate(Object validateObject) throws ValidationException;
}
