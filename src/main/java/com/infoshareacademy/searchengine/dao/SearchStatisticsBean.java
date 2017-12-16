package com.infoshareacademy.searchengine.dao;

import com.infoshareacademy.searchengine.domain.User;
import com.infoshareacademy.searchengine.repository.StatsRepository;
import com.infoshareacademy.searchengine.repository.UsersRepository;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.HashMap;
import java.util.Map;

@Stateful
public class SearchStatisticsBean implements SearchStatistics {

    @EJB
    private UsersRepositoryDao usersRepositoryDaoBean;

    @Override
    public void incStatCounter(int userId) {
        User user = usersRepositoryDaoBean.getUserById(userId);
        Map<User, Integer> statsRepo = StatsRepository.getStatsRepository();
        statsRepo.putIfAbsent(user, 0);
        statsRepo.computeIfPresent(user, (k, v) -> (++v));
    }

    @Override
    public HashMap<User, Integer> getStatsRepo(){
        return StatsRepository.getStatsRepository();
    }

    @Override
    public int getStatForUser(User user) {
        return StatsRepository.getStatsRepository().get(user);
    }

    @Override
    public String printStatistics() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<User, Integer> statsEntry : StatsRepository.getStatsRepository().entrySet()) {
            stringBuilder.append(statsEntry.getKey().toString())
                    .append(", liczba zapytan: ")
                    .append(statsEntry.getValue())
                    .append("<br>");
        }
        return stringBuilder.toString();
    }
}
