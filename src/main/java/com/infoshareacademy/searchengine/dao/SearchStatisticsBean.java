package com.infoshareacademy.searchengine.dao;

import com.infoshareacademy.searchengine.repository.StatsRepository;
import com.infoshareacademy.searchengine.repository.UsersRepository;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.Map;

@Stateful
public class SearchStatisticsBean implements SearchStatistics {

    @EJB
    private UsersRepositoryDao usersRepositoryDaoBean;

    @Override
    public void incStatCounter(int userId) {
        Map<Integer, Integer> statsRepo = StatsRepository.getStatsRepository();
        statsRepo.putIfAbsent(userId, 0);
        statsRepo.computeIfPresent(userId, (k, v) -> (++v));
    }

    @Override
    public int getStatForUser(int userId) {
        return StatsRepository.getStatsRepository().get(userId);
    }

    @Override
    public String printStatistics() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<Integer, Integer> statsEntry : StatsRepository.getStatsRepository().entrySet()) {
            stringBuilder.append(usersRepositoryDaoBean.getUserById(statsEntry.getKey()).toString())
                    .append(", liczba zapytan: ")
                    .append(statsEntry.getValue())
                    .append("<br>");
        }
        return stringBuilder.toString();
    }
}
