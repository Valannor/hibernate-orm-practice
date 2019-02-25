package com.practice.hibernate.dao;

import com.practice.hibernate.entity.Address;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * With Criteria
 */
public class AddressDAO extends DAO<Address> {

    public AddressDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Address read(long id) {
        Address address;

        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Address.class);

        //Property name has to be the same as the field name
        criteria.add(Restrictions.eq("id", id));
        address = (Address) criteria.uniqueResult();

        transaction.commit();
        session.close();

        return address;
    }

    @Override
    public void update(Address address) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(address);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(long id) {

        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(read(id));

        transaction.commit();
        session.close();
    }
}
