package com.infoshareacademy.searchengine.dao;

import com.infoshareacademy.searchengine.domain.User;
import com.infoshareacademy.searchengine.repository.StatsRepository;
import com.infoshareacademy.searchengine.repository.UsersRepository;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;

@Stateful
public class SearchStatisticsBean implements SearchStatistics {

    @EJB
    private UsersRepositoryDao usersRepositoryDaoBean;
    @EJB
    private StatsRepository statsRepository;

    @Override
    public void incStatCounter(int userId) {
        User user = usersRepositoryDaoBean.getUserById(userId);
        // Map<User, Integer> statsRepo = StatsRepository.getStatsRepository();
        statsRepository.getStatsRepository().putIfAbsent(user, 0);
        statsRepository.getStatsRepository().computeIfPresent(user, (k, v) -> (++v));
    }

    @Override
    public HashMap<User, Integer> getStatsRepo(){
        return statsRepository.getStatsRepository();
    }

    @Override
    public int getStatForUser(User user) {
        return statsRepository.getStatsRepository().get(user);
    }

    @Override
    public String printStatistics() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<User, Integer> statsEntry : statsRepository.getStatsRepository().entrySet()) {
            stringBuilder.append(statsEntry.getKey().toString())
                    .append(", liczba zapytan: ")
                    .append(statsEntry.getValue())
                    .append("<br>");
        }
        return stringBuilder.toString();
    }
}
