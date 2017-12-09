package com.infoshareacademy.searchengine.dao;

import com.infoshareacademy.searchengine.repository.StatsRepository;

import javax.ejb.Stateful;
import java.util.Map;

@Stateful
public class SearchStatisticsBean implements SearchStatistics {

    @Override
    public void incCounter(int userId) {
        Map<Integer, Integer> statsRepo = StatsRepository.getStatsRepository();
        statsRepo.putIfAbsent(userId, 0);
        statsRepo.computeIfPresent(userId, (k, v) -> (++v));
    }

    @Override
    public int getStatForUser(int userId) {
        return StatsRepository.getStatsRepository().get(userId);
    }
}
