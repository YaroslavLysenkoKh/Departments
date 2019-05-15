package dao.impl;

import dao.departments.DepartmentsDao;
import entity.Department;
import entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.hibernate.HibernateUtil;

import java.util.List;

public class DepartmentHiberDaoImpl implements DepartmentsDao {

    @Override
    public Department getByName(String name) {
        Department department;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            department = (Department) session.createQuery("from Department where Department.name =:name").setParameter("name", name);
        }
        return department;
    }

    @Override
    public List<Department> getAll() {
        List departmentList;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            departmentList = session.createQuery("from Department ", Department.class).list();
            for (Department department : (List<Department>) departmentList) {
                department.setEmployeeList((List<Employee>) session.createQuery("From Employee " +
                        "where department.id=:id_department").setParameter("id_department", department.getId()).list());
            }
        }
        return departmentList;
    }

    @Override
    public Department getById(Long id) {
        Department department;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            department = session.get(Department.class, id);
        }
        return department;
    }

    @Override
    public void delete(Department department) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
            session = HibernateUtil.getSessionFactory().openSession();
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
