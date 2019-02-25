package com.practice.hibernate.entity;

public class Address {

    private Long id;
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
