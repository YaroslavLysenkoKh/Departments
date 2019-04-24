package validator.employee.impl;

import entity.Employee;
import service.employee.EmployeeService;
import validator.employee.EmployeeValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeValidatorImpl implements EmployeeValidator {
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    private static final String PASSWORD_ERROR_MESSAGE = "minimum eight characters, at least one letter and one number";
    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]" +
            "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
            "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final String EMAIL_ERROR_MESSAGE = "email:mail must be like this: email.kh@department.com";
    private static final String INVALID_EMAIL_MESSAGE = "employee with such email already exists";
    private static final String INVALID_BIRTHDATE_MESSAGE = "birth date: cannot be after current date";
    private static final String INVALID_SALARY_MESSAGE = "salary: cannot be less or equals than 0";
    private EmployeeService employeeService;

    public EmployeeValidatorImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<String> validate(Employee employee) {

        List<String> errorMessages = new ArrayList<>();
        String password = employee.getPassword();
        String email = employee.getEmail();
        int salary = employee.getSalary();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date birthDate = null;
        Date now = null;
        try {
            birthDate = sdf.parse(String.valueOf(employee.getBirthDate()));
            now = sdf.parse(String.valueOf(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (password == null || !password.matches(PASSWORD_PATTERN)) {
            errorMessages.add(PASSWORD_ERROR_MESSAGE);
        }
        if (email == null || !email.matches(EMAIL_PATTERN)) {
            errorMessages.add(EMAIL_ERROR_MESSAGE);
        }

        if (employeeService.checkEmployeeExistenceByEmail(email)) {
            errorMessages.add(INVALID_EMAIL_MESSAGE);
        }
        if (birthDate.compareTo(now) > 0) {
            errorMessages.add(INVALID_BIRTHDATE_MESSAGE);

        }
        if (salary <= 0) {
            errorMessages.add(INVALID_SALARY_MESSAGE);
        }
        return errorMessages;
    }
}
