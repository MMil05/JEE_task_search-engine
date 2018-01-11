package com.infoshareacademy.searchengine.repository;

import com.infoshareacademy.searchengine.domain.User;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;

@Stateless
public class StatsRepository {

    @EJB
    private UsersRepository usersRepository;

    public HashMap<User, Integer> getStatsRepository() {
        HashMap<User, Integer> statsRepository = new HashMap<>();
        List<User> usersRepo = usersRepository.getUsersList();

        for (User user : usersRepo) {
            statsRepository.put(user, 0);
        }
        return statsRepository;
    }

}
