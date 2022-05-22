package com.gruntik.resttest.entity;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Table(name = "storef")
public class Store {

    @Id
    private String name;
    private Integer value;

    public Store() {
    }

    public Store(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
