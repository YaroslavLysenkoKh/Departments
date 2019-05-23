package comm.util.oval.employee;

import comm.entity.Employee;
import comm.service.employee.EmployeeService;
import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailCheck implements CheckWithCheck.SimpleCheck {

    @Autowired
    private EmployeeService employeeService;


    @Override
    public boolean isSatisfied(Object validatedObject, Object value) {
        Employee employee = (Employee) validatedObject;
        if (employee.getEmail().isEmpty())
            return false;
        if (employeeService.checkEmployeeExistenceByEmail(((Employee) validatedObject)))
            return false;
        return true;
    }
}
