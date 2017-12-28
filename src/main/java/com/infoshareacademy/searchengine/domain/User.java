package com.infoshareacademy.searchengine.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private int id;
    @Column
    private String name;
    private String surname;
    private String login;
    private int age;
    private Gender gender;
    // private List<Group> groups;
    @OneToMany
    @JoinColumn(name = "person_id")
    private List<Phone> phones = new ArrayList<>();

    /*public List<Group> getGroups() {
        return groups;
    }*/

    /* public void setGroups(List<Group> groups) {
        this.groups = groups;
    } */

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public User(int id, String name, String surname, String login, int age, Gender gender) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.age = age;
        this.gender = gender;
    }

    public User() {

    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return name + " " + surname + " (login: `" + login + "`, id: " + id + ")";
    }
}
