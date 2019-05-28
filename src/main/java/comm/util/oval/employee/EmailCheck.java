package comm.util.oval.employee;

import comm.entity.Employee;
import comm.service.employee.EmployeeService;
import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;


public class EmailCheck implements CheckWithCheck.SimpleCheck {

    @Autowired
    private EmployeeService employeeService;


    @Override
    public boolean isSatisfied(Object validatedObject, Object value) {
        Employee employee = (Employee) validatedObject;
        return employee.getEmail().isEmpty() || (employeeService.checkEmployeeExistenceByEmail(employee));
    }
}
