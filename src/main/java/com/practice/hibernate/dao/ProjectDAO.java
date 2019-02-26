package com.practice.hibernate.dao;

import com.practice.hibernate.entity.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * With JPQL/HQL
 */
public class ProjectDAO extends AbstractDAO<Project> {

    public ProjectDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Project read(long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Project p where p.id = :getID";
        Project project = (Project) session.createQuery(hql)
                .setParameter("getID", id)
                .uniqueResult();

        transaction.commit();
        session.close();

        return project;
    }

    @Override
    public void update(Project project) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "update Project p set p.title = :newTitle";
        session.createQuery(hql)
                .setParameter("newTitle", project.getTitle())
                .executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "delete from Project p where p.id = :deleteID";
        session.createQuery(hql)
                .setParameter("deleteID", id)
                .executeUpdate();

        transaction.commit();
        session.close();
    }
}
