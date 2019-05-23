package comm.util.oval.department;

import comm.entity.Department;
import comm.service.departments.DepartmentService;
import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NameCheck implements CheckWithCheck.SimpleCheck {

    @Autowired
    private DepartmentService departmentService;

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
