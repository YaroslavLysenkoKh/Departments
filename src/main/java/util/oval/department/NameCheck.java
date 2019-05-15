package util.oval.department;

import entity.Department;
import net.sf.oval.constraint.CheckWithCheck;
import service.departments.DepartmentService;
import service.impl.DepartmentHiberSerivceImpl;


public class NameCheck implements CheckWithCheck.SimpleCheck {
    private DepartmentService departmentService;

    public NameCheck() {
        this.departmentService = new DepartmentHiberSerivceImpl();
    }

    @Override
    public boolean isSatisfied(Object validatedObject, Object value) {
        Department department = (Department) validatedObject;
        if (department.getName().isEmpty())
            return false;
        if (departmentService.checkDepartmentExistenceByName(department))
            return false;
        return true;
    }
}
