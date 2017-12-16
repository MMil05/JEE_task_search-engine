package com.infoshareacademy.searchengine.dao;

import com.infoshareacademy.searchengine.domain.User;

import java.util.HashMap;

public interface SearchStatistics {
    void incStatCounter(int userId);
    int getStatForUser(User user);
    HashMap<User, Integer> getStatsRepo();

    String printStatistics();
}
