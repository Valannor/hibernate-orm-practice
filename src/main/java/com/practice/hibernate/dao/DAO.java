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

    abstract T create(T t);

    abstract T read(int id);

    abstract boolean update(T t);

    abstract boolean delete(T t);
}
