package com.practice.hibernate.dao;

import com.practice.hibernate.entity.Address;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class AddressDAO extends DAO<Address> {

    public AddressDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Address create(Address address) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        address = (Address) session.save(address);
        transaction.commit();

        session.close();

        return address;
    }

    @Override
    public Address read(int id) {
        Address address;

        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Address.class);

        criteria.add(Restrictions.gt("ID", id));
        address = (Address) criteria.uniqueResult();

        transaction.commit();

        session.close();

        return address;
    }

    @Override
    public boolean update(Address address) {
        return false;
    }

    @Override
    public boolean delete(Address address) {
        return false;
    }
}
