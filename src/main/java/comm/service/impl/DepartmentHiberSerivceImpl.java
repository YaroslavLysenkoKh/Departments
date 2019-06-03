package comm.service.impl;

import comm.dao.departments.DepartmentsDao;
import comm.entity.Department;
import comm.exception.IdException;
import comm.exception.ValidationException;
import comm.service.departments.DepartmentService;
import comm.util.oval.CustomValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentHiberSerivceImpl implements DepartmentService {

    private final DepartmentsDao departmentGenericDao;
    private final CustomValidator validator;

    public DepartmentHiberSerivceImpl(DepartmentsDao departmentGenericDao, CustomValidator validator) {
        this.departmentGenericDao = departmentGenericDao;
        this.validator = validator;
    }

    @Override
    @Transactional
    public List<Department> getAll() {
        return departmentGenericDao.getAll();
    }

    @Override
    @Transactional
    public Department getById(Long id) throws IdException {
        if (id == null) {
            throw new IdException();
        }
        return departmentGenericDao.getById(id);
    }

    @Override
    @Transactional
    public Department getByName(String name) {
        return departmentGenericDao.getByName(name);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws IdException {
        if (id == null) {
            throw new IdException();
        }
        Department department = getById(id);
        departmentGenericDao.delete(department);
    }

    @Override
    public boolean checkDepartmentExistenceByName(Department department) {
        Department tmpDepartment = getByName(department.getName());
        return (tmpDepartment == null || department.getId() != null && department.getId().equals(tmpDepartment.getId()));
    }

    @Override
    @Transactional
    public void addOrUpdate(Department department) throws ValidationException {
        validator.validate(department);
        departmentGenericDao.addOrUpdate(department);
    }
}
