package com.infoshareacademy.searchengine.dao;

public interface SearchStatistics {
    void incCounter(int userId);

    int getStatForUser(int userId);
}
