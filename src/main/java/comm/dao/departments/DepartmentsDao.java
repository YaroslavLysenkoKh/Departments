package comm.dao.departments;

import comm.dao.GenericDao;
import comm.entity.Department;

public interface DepartmentsDao extends GenericDao<Department> {
    Department getByName(String name);
}
