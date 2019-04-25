package service.employee.impl;

import dao.employees.EmployeesDao;
import dao.employees.impl.EmployeesDaoImpl;
import entity.Employee;
import service.employee.EmployeeService;
import transaction.TransactionManager;
import transaction.impl.TransactionManagerImpl;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final TransactionManager transactionManager;
    private final EmployeesDao employeesDao;

    public EmployeeServiceImpl() {
        this.transactionManager = new TransactionManagerImpl();
        this.employeesDao = new EmployeesDaoImpl();
    }

    @Override
    public List<Employee> getAll() {
        return transactionManager.doInTransaction(employeesDao::getAll);
    }

    @Override
    public boolean add(Employee employee) {
        transactionManager.doInTransaction(connection -> {
            employeesDao.add(employee, connection);
            return null;
        });
        return true;
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
    public boolean update(Employee employee) {
        transactionManager.doInTransaction(connection -> {
            employeesDao.update(employee, connection);
            return null;
        });
        return true;
    }

    @Override
    public List<Employee> getAllByDepartmentId(Long id) {
        return transactionManager.doInTransaction(connection -> employeesDao.getAllByDepartmentId(id, connection));
    }

    @Override
    public boolean checkEmployeeExistenceByEmail(String email) {
        Employee employee = getEmployeeByEmail(email);
        if (employee != null) {
            if (employee.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkEmployeeExistenceByDepartmentId(Long id) {
        List<Employee> employees = getAllByDepartmentId(id);
        if (employees != null) {
            return true;
        }
        return false;
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return transactionManager.doInTransaction(connection -> employeesDao.getEmployeeByEmail(email, connection));
    }
}
