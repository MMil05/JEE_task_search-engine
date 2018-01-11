package com.infoshareacademy.searchengine.dao;

import com.infoshareacademy.searchengine.domain.User;
import com.infoshareacademy.searchengine.interceptors.AddUserCheckGenderInterceptor;
import com.infoshareacademy.searchengine.interceptors.AddUserLogInterceptor;
import com.infoshareacademy.searchengine.repository.UsersRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UsersRepositoryDaoBean implements UsersRepositoryDao, UsersRepositoryDaoRemote {

    @EJB
    private UsersRepository usersRepository;

    @Override
    @Interceptors({AddUserLogInterceptor.class, AddUserCheckGenderInterceptor.class})
    public void addUser(User user) {
        // UsersRepository.getRepository().add(user);
        usersRepository.addUser(user);
    }

    public User getUserById(int id) {
        return usersRepository.getUserById(id);
    }

    public User getUserByLogin(String login) {
        return usersRepository.getUserByLogin(login);
    }

    public List<User> getUsersList() {
        return usersRepository.getUsersList();
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
