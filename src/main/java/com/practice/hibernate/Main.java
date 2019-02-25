package com.practice.hibernate;

import com.practice.hibernate.dao.AddressDAO;
import com.practice.hibernate.dao.ProjectDAO;
import com.practice.hibernate.entity.Address;
import com.practice.hibernate.entity.Project;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        /**
         * Entities for testing
         */
        Address address = new Address();
        address.setPosition("USA, Pennsylvania avenue 1600");

        Project project = new Project();
        project.setTitle("AmWorks");
        Set<Project> projects = new HashSet<>();
        projects.add(project);


        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        /**
         *AddressDAO
         */
        AddressDAO addressDAO = new AddressDAO(sessionFactory);
        //C
        long addressId = addressDAO.create(address);
        //R
        Address addressResult = addressDAO.read(addressId);
        System.out.println(addressResult);
        //U
        addressResult.setPosition("Newton");
        addressDAO.update(addressResult);
        System.out.println(addressDAO.read(addressId));
        //D
        addressDAO.delete(addressId);
        System.out.println(addressDAO.read(addressId));

        /**
         * ProjectDAO
         */
        ProjectDAO projectDAO = new ProjectDAO(sessionFactory);
        //C
        long projectID = projectDAO.create(project);
        //R
        Project projectResult = projectDAO.read(projectID);
        System.out.println(projectResult);
        //U
        projectResult.setTitle("Newton");
        projectDAO.update(projectResult);
        System.out.println(projectDAO.read(projectID));
        //D
        projectDAO.delete(projectID);
        System.out.println(projectDAO.read(projectID));


        sessionFactory.close();
    }
}
