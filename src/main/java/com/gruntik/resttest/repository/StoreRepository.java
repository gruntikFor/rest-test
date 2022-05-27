package com.gruntik.resttest.repository;

import com.gruntik.resttest.entity.Store;

import java.util.List;

public interface StoreRepository {

    void save(Store store);

    List<Store> findAll();

    boolean existsByName(String name);

    int deleteByName(String name);

    void deleteAll();
}
