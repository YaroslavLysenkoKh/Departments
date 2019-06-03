package comm.service.impl;

import comm.dao.employees.EmployeesDao;
import comm.entity.Employee;
import comm.exception.IdException;
import comm.exception.ValidationException;
import comm.service.departments.DepartmentService;
import comm.service.employee.EmployeeService;
import comm.util.oval.CustomValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeHiberServiceImpl implements EmployeeService {

    private final CustomValidator validator;
    private final EmployeesDao employeesDao;
    private final DepartmentService departmentService;

    public EmployeeHiberServiceImpl(CustomValidator validator, EmployeesDao employeesDao, DepartmentService departmentService) {
        this.validator = validator;
        this.employeesDao = employeesDao;
        this.departmentService = departmentService;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Employee getById(Long id) {
        return employeesDao.getById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws IdException {
        if (id == null) {
            throw new IdException();
        }
        Employee employee = getById(id);
        employeesDao.delete(employee);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Employee> getAllByDepartmentId(Long id) throws IdException {
        if (id == null) {
            throw new IdException();
        }
        return employeesDao.getAllByDepartmentId(id);
    }

    @Override
    public boolean checkEmployeeExistenceByEmail(Employee employee) {
        Employee tmpEmployee = getEmployeeByEmail(employee.getEmail());
        return (tmpEmployee == null || employee.getId() != null && employee.getId().equals(tmpEmployee.getId()));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Employee getEmployeeByEmail(String email) {
        return employeesDao.getEmployeeByEmail(email);
    }

    @Override
    @Transactional
    public void addOrUpdate(Employee employee, Long departmentId) throws ValidationException, IdException {
        if (departmentId == null) {
            throw new IdException();
        }
        employee.setDepartment(departmentService.getById(departmentId));
        validator.validate(employee);
        employeesDao.addOrUpdate(employee);
    }
}