package comm.service.impl;

import comm.dao.employees.EmployeesDao;
import comm.entity.Employee;
import comm.exception.ValidationException;
import comm.service.employee.EmployeeService;
import comm.util.oval.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeHiberServiceImpl implements EmployeeService {

    @Autowired
    private CustomValidator validator;

    @Autowired
    private EmployeesDao employeesDao;

    public EmployeeHiberServiceImpl() {
    }

    @Override
    public Employee getById(Long id) {
        return employeesDao.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        Employee employee = getById(id);
        employeesDao.delete(employee);
    }

    @Override
    public List<Employee> getAllByDepartmentId(Long id) {
        return employeesDao.getAllByDepartmentId(id);
    }

    @Override
    public boolean checkEmployeeExistenceByEmail(Employee employee) {
        Employee tmpEmployee = getEmployeeByEmail(employee.getEmail());
        if (tmpEmployee != null) {
            if (tmpEmployee.getId() == employee.getId() && tmpEmployee.getEmail().equals(employee.getEmail())) {
                return false;
            }
            if (tmpEmployee.getEmail().equals(employee.getEmail())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return employeesDao.getEmployeeByEmail(email);
    }

    @Override
    public void addOrUpdate(Employee employee) throws ValidationException {
        validator.validate(employee);
        employeesDao.addOrUpdate(employee);
    }
}
