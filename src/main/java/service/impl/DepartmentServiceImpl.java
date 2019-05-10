package service.impl;

import dao.departments.DepartmentsDao;
import dao.impl.DepartmentsDaoImpl;
import entity.Department;
import exception.ValidationException;
import service.departments.DepartmentService;
import transaction.TransactionManager;
import transaction.impl.TransactionManagerImpl;
import util.validator.CustomValidator;
import util.validator.impl.CustomValidatorImpl;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private final CustomValidator validator;
    private final TransactionManager transactionManager;
    private final DepartmentsDao departmentsDao;

    public DepartmentServiceImpl() {
        this.validator = new CustomValidatorImpl();
        this.transactionManager = new TransactionManagerImpl();
        this.departmentsDao = new DepartmentsDaoImpl();
    }

    @Override
    public List<Department> getAll() {
        return transactionManager.doInTransaction(departmentsDao::getAll);
    }

    @Override
    public Department getById(Long id) {
        return transactionManager.doInTransaction(connection -> departmentsDao.getById(id, connection));
    }

    @Override
    public boolean deleteById(Long id) {
        transactionManager.doInTransaction(connection -> {
            departmentsDao.deleteById(id, connection);
            return null;
        });
        return true;
    }

    @Override
    public boolean checkDepartmentExistenceByName(Department department) {
        Department tmpDepartment = getById(department.getId());
        if (tmpDepartment != null) {
            if (tmpDepartment.getName().equals(department.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addOrUpdate(Department department) throws ValidationException {
        validator.validate(department);
        transactionManager.doInTransaction(connection -> {
            departmentsDao.addOrUpdate(department, connection);
            return null;
        });
        return true;
    }
}
