package com.practice.hibernate.dao;

import org.hibernate.SessionFactory;

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

    abstract long create(T t);

    abstract T read(long id);

    abstract void update(T t);

    abstract void delete(long id);
}
