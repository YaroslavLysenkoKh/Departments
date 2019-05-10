package service.employee.impl;

import dao.employees.EmployeesDao;
import dao.employees.impl.EmployeesDaoImpl;
import entity.Employee;
import exception.ValidationException;
import service.employee.EmployeeService;
import transaction.TransactionManager;
import transaction.impl.TransactionManagerImpl;
import util.validator.CustomValidator;
import util.validator.impl.CustomValidatorImpl;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private final CustomValidator validator;
    private final TransactionManager transactionManager;
    private final EmployeesDao employeesDao;

    public EmployeeServiceImpl() {
        this.transactionManager = new TransactionManagerImpl();
        this.employeesDao = new EmployeesDaoImpl();
        this.validator = new CustomValidatorImpl();
    }

    @Override
    public List<Employee> getAll() {
        return transactionManager.doInTransaction(employeesDao::getAll);
    }

    @Override
    public Employee getById(Long id) {
        return transactionManager.doInTransaction(connection -> employeesDao.getById(id, connection));
    }

    @Override
    public boolean deleteById(Long id) {
        transactionManager.doInTransaction(connection -> {
            employeesDao.deleteById(id, connection);
            return null;
        });
        return true;
    }

    @Override
    public List<Employee> getAllByDepartmentId(Long id) {
        return transactionManager.doInTransaction(connection -> employeesDao.getAllByDepartmentId(id, connection));
    }

    @Override
    public boolean checkEmployeeExistenceByEmail(Employee employee) {
        Employee tmpEmployee = getEmployeeByEmail(employee.getEmail());
        if (employee != null) {
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
        return transactionManager.doInTransaction(connection -> employeesDao.getEmployeeByEmail(email, connection));
    }

    @Override
    public boolean addOrUpdate(Employee employee) throws ValidationException {
        validator.validate(employee);
        transactionManager.doInTransaction(connection -> {
            employeesDao.addOrUpdate(employee, connection);
            return null;
        });
        return true;
    }
}
