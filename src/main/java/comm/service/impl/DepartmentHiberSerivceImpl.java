package comm.service.impl;

import comm.dao.departments.DepartmentsDao;
import comm.entity.Department;
import comm.exception.ValidationException;
import comm.service.departments.DepartmentService;
import comm.util.oval.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentHiberSerivceImpl implements DepartmentService {
    @Autowired
    private DepartmentsDao departmentGenericDao;
    @Autowired
    private CustomValidator validator;

    public DepartmentHiberSerivceImpl() {
    }

    @Override
    @Transactional
    public List<Department> getAll() {
        return departmentGenericDao.getAll();
    }

    @Override
    @Transactional
    public Department getById(Long id) {
        return departmentGenericDao.getById(id);
    }

    @Override
    @Transactional
    public Department getByName(String name) {
        return departmentGenericDao.getByName(name);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Department department = getById(id);
        departmentGenericDao.delete(department);
    }

    @Override
    public boolean checkDepartmentExistenceByName(Department department) {
        List<Department> tmpDepartments = getAll();
        for (Department department1 : tmpDepartments) {
            if (department.getId() != null) {
                return department1.getName().equals(department.getName()) && department1.getId() == department.getId() ? false : true;
            } else {
                return department1.getName().equals(department.getName()) ? false : true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public void addOrUpdate(Department department) throws ValidationException {
        validator.validate(department);
        departmentGenericDao.addOrUpdate(department);
    }
}
