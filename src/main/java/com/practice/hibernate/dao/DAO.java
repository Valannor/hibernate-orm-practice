package com.practice.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class DAO<T> {

    private SessionFactory sessionFactory;

    public DAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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
