package comm.dao.impl;

import comm.dao.departments.DepartmentsDao;
import comm.entity.Department;
import comm.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentHiberDaoImpl implements DepartmentsDao {

    private final SessionFactory sessionFactory;

    public DepartmentHiberDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Department getByName(String name) {
        Department department;
        Session session = sessionFactory.getCurrentSession();
        department = (Department) session.createQuery("from Department d where d.name =:name").setParameter("name", name).uniqueResult();
        return department;
    }

    @Override
    public List<Department> getAll() {
        List departmentList;
        Session session = sessionFactory.getCurrentSession();
        departmentList = session.createQuery("from Department ", Department.class).list();
        for (Department department : (List<Department>) departmentList) {
            department.setEmployeeList((List<Employee>) session.createQuery("From Employee " +
                    "where department.id=:id_department").setParameter("id_department", department.getId()).list());
        }
        return departmentList;
    }


    @Override
    public Department getById(Long id) {
        Department department;
        Session session = sessionFactory.getCurrentSession();
        department = session.get(Department.class, id);
        return department;
    }

    @Override
    public void delete(Department department) {
        Session session = sessionFactory.getCurrentSession();
        if (department != null) {
            session.delete(department);
        }
    }

    @Override
    public void addOrUpdate(Department department) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(department);
    }
}
