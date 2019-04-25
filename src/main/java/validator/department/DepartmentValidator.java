package validator.department;

import entity.Department;

import java.util.List;

public interface DepartmentValidator {

    List<String> validate(Department department);
}
