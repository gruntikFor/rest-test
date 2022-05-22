package com.gruntik.resttest.dao;

import com.gruntik.resttest.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, String> {

    boolean existsByName(String name);

    int deleteByName(String name);
}
