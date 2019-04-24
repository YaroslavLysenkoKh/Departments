package service.departments.impl;

import dao.departments.DepartmentsDao;
import dao.departments.impl.DepartmentsDaoImpl;
import entity.Department;
import service.departments.DepartmentService;
import transaction.TransactionManager;
import transaction.impl.TransactionManagerImpl;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private final TransactionManager transactionManager;
    private final DepartmentsDao departmentsDao;

    public DepartmentServiceImpl() {
        this.transactionManager = new TransactionManagerImpl();
        this.departmentsDao = new DepartmentsDaoImpl();
    }

    @Override
    public List<Department> getAll() {
        return transactionManager.doInTransaction(departmentsDao::getAll);
    }

    @Override
    public boolean add(Department department) {
        transactionManager.doInTransaction(connection -> {
            departmentsDao.add(department, connection);
            return null;
        });
        return true;
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
    public boolean update(Department department) {
        transactionManager.doInTransaction(connection -> {
            departmentsDao.update(department, connection);
            return null;
        });
        return true;
    }
}
