package com.gruntik.resttest;

import com.gruntik.resttest.dao.StoreRepository;
import com.gruntik.resttest.entity.Store;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@WebAppConfiguration
@SpringBootTest
class RestTestApplicationTests {

    @Autowired
    StoreRepository storeRepository;

    @Test
    void save() {
        storeRepository.save(new Store("igor", 23));
        Assertions.assertTrue(storeRepository.existsByName("igor"));
    }

    @Test
    void existsByName() {
        storeRepository.deleteAll();
        storeRepository.save(new Store("igor", 23));

        Assertions.assertTrue(storeRepository.existsByName("igor"));
    }

    @Transactional
    @Test
    void deleteByName() {
        storeRepository.deleteAll();
        storeRepository.save(new Store("igor", 23));

        Assertions.assertEquals(1, storeRepository.deleteByName("igor"));
    }

}
