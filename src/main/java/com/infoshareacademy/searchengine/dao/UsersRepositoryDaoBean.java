package com.infoshareacademy.searchengine.dao;

import com.infoshareacademy.searchengine.domain.User;
import com.infoshareacademy.searchengine.interceptors.AddUserCheckGenderInterceptor;
import com.infoshareacademy.searchengine.interceptors.AddUserLogInterceptor;
import com.infoshareacademy.searchengine.repository.UsersRepository;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UsersRepositoryDaoBean implements UsersRepositoryDao, UsersRepositoryDaoRemote {

    @PersistenceContext(unitName = "pUnit")
    private EntityManager entityManager;

    @Override
    @Interceptors({AddUserLogInterceptor.class, AddUserCheckGenderInterceptor.class})
    public void addUser(User user) {
        UsersRepository.getRepository().add(user);
    }

    public User getUserById(int id) {
        List<User> userList = UsersRepository.getRepository();
        for (User user : userList){
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User getUserByLogin(String login) {
        List<User> userList = UsersRepository.getRepository();
        for (User user : userList){
            if (user.getLogin().equals(login)){
                return user;
            }

        }
        return null;
    }

    public List<User> getUsersList() {
        return UsersRepository.getRepository();
    }

    @Override
    public List<String> getUsersNames() {
        List<String> usersNames = new ArrayList<>();
        List<User> userList = getUsersList();

        for (User user : userList){
            usersNames.add(user.getName());
        }
        return usersNames;
    }
}
