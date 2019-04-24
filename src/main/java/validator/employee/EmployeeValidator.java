package validator.employee;

import entity.Employee;

import java.util.List;

public interface EmployeeValidator {

    List<String> validate(Employee employee);
}
