package com.infoshareacademy.searchengine.repository;

import com.infoshareacademy.searchengine.domain.Gender;
import com.infoshareacademy.searchengine.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UsersRepository {

    @PersistenceContext(unitName = "pUnit")
    private EntityManager entityManager;

    public boolean addUser(User user) {
        entityManager.persist(user);
        System.out.println("Dodany użytkownik: " + user.toString());
        return true;
    }

    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    public User getUserByLogin(String login) {
        return (User) entityManager.createNamedQuery("getUserByLogin")
                .setParameter("login", login)
                .getSingleResult();
    }

    public List<User> getUsersList() {
        return (List<User>) entityManager.createNamedQuery("getAll")
                .getResultList();
    }

}
