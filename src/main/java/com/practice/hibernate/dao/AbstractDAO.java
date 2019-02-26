package com.practice.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDAO<T> {

    private SessionFactory sessionFactory;

    public AbstractDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * There is no insert command in JPQL and HQL. And it is much easier to use session methods for save, than create an SQL statement.
     * Although, parameters in query has to be written like this :parameterName. Else-way Hibernate won't understand query.
     */
    public long create(T t) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        long id = (Long) session.save(t);

        transaction.commit();
        session.close();

        return id;
    }

    public abstract T read(long id);

    public abstract void update(T t);

    public abstract void delete(long id);
}
