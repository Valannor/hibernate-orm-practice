package com.practice.hibernate;

import com.practice.hibernate.dao.AddressDAO;
import com.practice.hibernate.entity.Address;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {

        //Entities for testing
        Address address = new Address();
        address.setPosition("USA, Pennsylvania avenue 1600");


        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();


        AddressDAO addressDAO = new AddressDAO(sessionFactory);
        //C
        long addressId = addressDAO.create(address);
        //R
        Address result = addressDAO.read(addressId);
        System.out.println(result);
        //U
        result.setPosition("Newton");
        addressDAO.update(result);
        System.out.println(addressDAO.read(addressId));
        //D
        addressDAO.delete(addressId);
        System.out.println(addressDAO.read(addressId));




        sessionFactory.close();
    }
}
