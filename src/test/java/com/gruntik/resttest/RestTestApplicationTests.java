package com.gruntik.resttest;

import com.gruntik.resttest.entity.Store;
import com.gruntik.resttest.service.StoreServiceImpl;
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
    StoreServiceImpl storeService;

    @Test
    void save() {
        storeService.save(new Store("igor", 23));
        Assertions.assertTrue(storeService.existsByName("igor"));
    }

    @Test
    void existsByName() {
        storeService.deleteAll();
        storeService.save(new Store("igor", 23));

        Assertions.assertTrue(storeService.existsByName("igor"));
    }

    @Transactional
    @Test
    void deleteByName() {
        storeService.deleteAll();
        storeService.save(new Store("igor", 23));

        Assertions.assertEquals(1, storeService.deleteByName("igor"));
    }

}
