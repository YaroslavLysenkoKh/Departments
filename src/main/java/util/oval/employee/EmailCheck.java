package util.oval.employee;

import entity.Employee;
import net.sf.oval.constraint.CheckWithCheck;
import service.employee.EmployeeService;
import service.employee.impl.EmployeeServiceImpl;

public class EmailCheck implements CheckWithCheck.SimpleCheck {
    private EmployeeService employeeService;

    public EmailCheck() {
        this.employeeService = new EmployeeServiceImpl();
    }

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
