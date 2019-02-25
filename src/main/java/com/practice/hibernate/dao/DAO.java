package com.practice.hibernate.dao;

public interface DAO <T> {
    void create(T t);
    T read();
    boolean update(T t);
    boolean delete(T t);
}
