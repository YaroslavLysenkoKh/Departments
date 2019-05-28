package comm.service.impl;

import comm.dao.employees.EmployeesDao;
import comm.entity.Employee;
import comm.exception.ValidationException;
import comm.service.employee.EmployeeService;
import comm.util.oval.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeHiberServiceImpl implements EmployeeService {

    @Autowired
    private CustomValidator validator;

    @Autowired
    private EmployeesDao employeesDao;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.SERIALIZABLE)
    public Employee getById(Long id) {
        return employeesDao.getById(id);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteById(Long id) {
        Employee employee = getById(id);
        employeesDao.delete(employee);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.SERIALIZABLE)
    public List<Employee> getAllByDepartmentId(Long id) {
        return employeesDao.getAllByDepartmentId(id);
    }

    @Override
    public boolean checkEmployeeExistenceByEmail(Employee employee) {
        Employee tmpEmployee = getEmployeeByEmail(employee.getEmail());
        return (tmpEmployee == null || employee.getId() != null && employee.getId().equals(tmpEmployee.getId()));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.SERIALIZABLE)
    public Employee getEmployeeByEmail(String email) {
        return employeesDao.getEmployeeByEmail(email);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void addOrUpdate(Employee employee) throws ValidationException {
        validator.validate(employee);
        employeesDao.addOrUpdate(employee);
    }
}