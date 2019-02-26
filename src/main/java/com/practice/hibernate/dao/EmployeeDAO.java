package com.practice.hibernate.dao;

import com.practice.hibernate.entity.Employee;
import com.practice.hibernate.entity.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigInteger;
import java.util.Set;

/**
 * With SQL
 */
public class EmployeeDAO extends AbstractDAO<Employee> {

    public EmployeeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public long create(Employee employee) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql1 = "insert into Employee(name, address_id) values (:newName, :newAddress)";
        session.createSQLQuery(sql1)
                .setParameter("newName", employee.getName())
                .setParameter("newAddress", employee.getAddress().getId())
                .executeUpdate();

        String sql2 = "select e.id from Employee as e where e.name = :employeeName";
        Long employeeID = ((BigInteger) session.createSQLQuery(sql2)
                .setParameter("employeeName", employee.getName())
                .uniqueResult()).longValue();

        Set<Project> projects = employee.getProjects();
        createEmployeeProjectsAssociation(session, employeeID, projects);

        transaction.commit();
        session.close();

        return employeeID;
    }

    private void createEmployeeProjectsAssociation(Session session, long employeeID, Set<Project> projects) {
        String sql3 = "insert into Empl_Proj(employee_id, project_id) " +
                "values (:employeeID, (select p.id from Project as p where p.title = :projectTitle))";
        for (Project project : projects) {
            session.createSQLQuery(sql3)
                    .setParameter("employeeID", employeeID)
                    .setParameter("projectTitle", project.getTitle())
                    .executeUpdate();
        }
    }

    @Override
    public Employee read(long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "select e.* from Employee as e where e.id = :getID";
        Employee result = (Employee) session.createSQLQuery(sql)
                .setParameter("getID", id)
                .addEntity(Employee.class)
                .uniqueResult();

        transaction.commit();
        session.close();

        return result;
    }

    @Override
    public void update(Employee employee) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "update Employee e set e.name = :newName, e.address_id = :newAddress";
        session.createSQLQuery(sql)
                .setParameter("newName", employee.getName())
                .setParameter("newAddress", employee.getAddress())
                .executeUpdate();

        deleteProjects(employee.getId());
        createEmployeeProjectsAssociation(session, employee.getId(), employee.getProjects());

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        deleteProjects(id);

        String sql = "delete from Employee e where e.id = :deleteID";
        session.createSQLQuery(sql)
                .setParameter("deleteID", id)
                .executeUpdate();

        transaction.commit();
        session.close();
    }

    private void deleteProjects(long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "delete from Empl_Proj ep where ep.employee_id = :deleteID";
        session.createSQLQuery(sql)
                .setParameter("deleteID", id)
                .executeUpdate();

        transaction.commit();
        session.close();
    }
}
