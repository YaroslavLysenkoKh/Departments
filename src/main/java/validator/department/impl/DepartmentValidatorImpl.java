package validator.department.impl;

import entity.Department;
import service.departments.DepartmentService;
import service.departments.impl.DepartmentServiceImpl;
import validator.department.DepartmentValidator;

import java.util.ArrayList;
import java.util.List;

public class DepartmentValidatorImpl implements DepartmentValidator {
    private static final String NAME_PATTERN = "\\w{2,15}";
    private static final String NAME_ERROR_MESSAGE = "minimum two characters";
    private static final String INVALID_DEPARTMENT_MESSAGE = "department with such name already exists";
    private DepartmentService departmentService;

    public DepartmentValidatorImpl() {
        this.departmentService = new DepartmentServiceImpl();
    }

    @Override
    public List<String> validate(Department department) {

        List<String> errorMessages = new ArrayList<>();
        String name = department.getName();


        if (name == null || !name.matches(NAME_PATTERN)) {
            errorMessages.add(NAME_ERROR_MESSAGE);
        }
        if (departmentService.checkDepartmentExistenceByName(name)) {
            errorMessages.add(INVALID_DEPARTMENT_MESSAGE);
        }
        return errorMessages;
    }
}
