package comm.dao.impl;

import comm.dao.departments.DepartmentsDao;
import comm.entity.Department;
import comm.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentHiberDaoImpl implements DepartmentsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Department getByName(String name) {
        Department department;
        try (Session session = sessionFactory.openSession()) {
            department = (Department) session.createQuery("from Department d where d.name =:name").setParameter("name", name);
        }
        return department;
    }

    @Override
    public List<Department> getAll() {
        List departmentList;
        Session session = sessionFactory.openSession();
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
        try (Session session = sessionFactory.openSession()) {
            department = session.get(Department.class, id);
        }
        return department;
    }

    @Override
    public void delete(Department department) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if (department != null) {
                session.delete(department);
                flushAndClear(session);
            }
            transaction.commit();
        } catch (Exception e) {
            rollBack(transaction);
        } finally {
            close(session);
        }
    }

    @Override
    public void addOrUpdate(Department department) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(department);
            flushAndClear(session);
            transaction.commit();
        } catch (Exception e) {
            rollBack(transaction);
        } finally {
            close(session);
        }
    }

    private void rollBack(Transaction transaction) {
        if (transaction != null) {
            transaction.rollback();
        }
    }

    private void close(Session session) {
        if (session != null) {
            session.close();
        }
    }

    private void flushAndClear(Session session) {
        session.flush();
        session.clear();
    }
}
