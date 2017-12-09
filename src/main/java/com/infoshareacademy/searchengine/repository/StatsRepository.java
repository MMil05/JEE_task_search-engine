package com.infoshareacademy.searchengine.repository;

import com.infoshareacademy.searchengine.domain.User;


import java.util.HashMap;


public class StatsRepository {
    // userid, counter
    private static HashMap<Integer, Integer> statsRepository = new HashMap<>();

    public static HashMap<Integer, Integer> getStatsRepository() {
        return statsRepository;
    }
}
