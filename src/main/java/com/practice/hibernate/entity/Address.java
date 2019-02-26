package com.practice.hibernate.entity;


import javax.persistence.*;

@Entity
@Table(name="ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ADDRESS")
    private String position;

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", position='" + position + '\'' +
                '}';
    }
}
