package comm.service.impl;

import comm.dao.departments.DepartmentsDao;
import comm.entity.Department;
import comm.exception.ValidationException;
import comm.service.departments.DepartmentService;
import comm.util.oval.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentHiberSerivceImpl implements DepartmentService {
    @Autowired
    private DepartmentsDao departmentGenericDao;
    @Autowired
    private CustomValidator validator;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Department> getAll() {
        return departmentGenericDao.getAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Department getById(Long id) {
        return departmentGenericDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Department getByName(String name) {
        return departmentGenericDao.getByName(name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void deleteById(Long id) {
        Department department = getById(id);
        departmentGenericDao.delete(department);
    }

    @Override
    public boolean checkDepartmentExistenceByName(Department department) {
        Department tmpDepartment = getByName(department.getName());
        if (department.getId() != null) {
            return tmpDepartment.getName().equals(department.getName()) && tmpDepartment.getId() == department.getId() ? false : true;
        } else {
            return tmpDepartment.getName().equals(department.getName()) ? true : false;
        }
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void addOrUpdate(Department department) throws ValidationException {
        validator.validate(department);
        departmentGenericDao.addOrUpdate(department);
    }
}
