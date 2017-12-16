package com.infoshareacademy.searchengine.repository;

import com.infoshareacademy.searchengine.domain.User;


import java.util.HashMap;


public class StatsRepository {
    private static HashMap<User, Integer> statsRepository = new HashMap<>();

    public static HashMap<User, Integer> getStatsRepository() {
        if (statsRepository.size() == 0)
            initalizeFromUsersRepository();
        return statsRepository;
    }

    private static void initalizeFromUsersRepository() {
        for (User user : UsersRepository.getRepository()) {
            statsRepository.put(user, 0);
        }
    }
}
