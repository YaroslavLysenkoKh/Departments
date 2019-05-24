package comm.dao.impl;

import comm.dao.departments.DepartmentsDao;
import comm.entity.Department;
import comm.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentHiberDaoImpl implements DepartmentsDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Department getByName(String name) {
        Department department;
        Session session = getSession();
        department = (Department) session.createQuery("from Department d where d.name =:name").setParameter("name", name).uniqueResult();
        return department;
    }

    @Override
    public List<Department> getAll() {
        List departmentList;
        Session session = getSession();
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
        Session session = getSession();
        department = session.get(Department.class, id);
        return department;
    }

    @Override
    public void delete(Department department) {
        Session session = getSession();
        if (department != null) {
            session.delete(department);
        }
    }

    @Override
    public void addOrUpdate(Department department) {
        Session session = getSession();
        session.saveOrUpdate(department);
    }
}
