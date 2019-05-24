package comm.dao.impl;

import comm.dao.employees.EmployeesDao;
import comm.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeHiberDaoImpl implements EmployeesDao {


    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Employee> getAllByDepartmentId(Long id) {
        List employeeList;
        try (Session session = getSession()) {
            employeeList = session.createQuery("from Employee where department.id =:id").setParameter("id", id).list();
        }
        return employeeList;
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        Employee employee;
        try (Session session = getSession()) {
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
        try (Session session = getSession()) {
            employee = session.get(Employee.class, id);
        }
        return employee;
    }

    @Override
    public void delete(Employee employee) {
        Session session = getSession();
        if (employee != null) {
            session.delete(employee);
        }
    }

    @Override
    public void addOrUpdate(Employee employee) {
        Session session = getSession();
        session.saveOrUpdate(employee);
    }
}
