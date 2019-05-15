package dao.impl;

import dao.employees.EmployeesDao;
import entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.hibernate.HibernateUtil;

import java.util.List;

public class EmployeeHiberDaoImpl implements EmployeesDao {
    @Override
    public List<Employee> getAllByDepartmentId(Long id) {
        List employeeList;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            employeeList = session.createQuery("from Employee where department.id =:id").setParameter("id", id).list();
        }
        return employeeList;
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        Employee employee;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            employee = (Employee) session.createQuery("from Employee e where e.email =:email").setParameter("email", email).list().get(0);
        }
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        //NOP
        return null;
    }

    @Override
    public Employee getById(Long id) {
        Employee employee;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            employee = session.get(Employee.class, id);
        }
        return employee;
    }

    @Override
    public void delete(Employee employee) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            if (employee != null) {
                session.delete(employee);
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
    public void addOrUpdate(Employee employee) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
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
