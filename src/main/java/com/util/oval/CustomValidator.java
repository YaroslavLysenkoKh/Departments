package com.util.oval;

import com.exception.ValidationException;

public interface CustomValidator {

    void validate(Object validateObject) throws ValidationException;
}
