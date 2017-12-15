package com.infoshareacademy.searchengine.dao;

public interface SearchStatistics {
    void incStatCounter(int userId);
    int getStatForUser(int userId);

    String printStatistics();
}
