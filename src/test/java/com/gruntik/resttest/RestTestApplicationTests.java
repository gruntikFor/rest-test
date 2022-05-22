package com.gruntik.resttest;

import com.gruntik.resttest.dao.StoreRepository;
import com.gruntik.resttest.entity.Store;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RestTestApplicationTests {

    @Autowired
    StoreRepository storeRepository;

    @Test
    void testRepo() {
        storeRepository.save(new Store("igor", 23));

        Assertions.assertTrue(storeRepository.existsByName("igor"));
    }

}
