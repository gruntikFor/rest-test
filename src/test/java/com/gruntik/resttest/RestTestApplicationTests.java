package com.gruntik.resttest;

import com.gruntik.resttest.entity.Store;
import com.gruntik.resttest.repository.StoreRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@WebAppConfiguration
@SpringBootTest
class RestTestApplicationTests {

    @Autowired
    StoreRepository storeRepository;

    @Transactional
    @BeforeEach
    public void deleteAll() {
        storeRepository.deleteAll();
    }

    @Test
    void save() {
        storeRepository.save(new Store("igor", 23));
        Assertions.assertTrue(storeRepository.existsByName("igor"));
    }

    @Test
    void existsByName() {
        storeRepository.save(new Store("igor", 23));

        Assertions.assertTrue(storeRepository.existsByName("igor"));
    }

    @Test
    void findAll() {
        storeRepository.save(new Store("igor", 23));
        storeRepository.save(new Store("vas", 30));

        Assertions.assertEquals(2, storeRepository.findAll().size());
    }

    @Transactional
    @Test
    void deleteByName() {
        storeRepository.save(new Store("igor", 23));

        Assertions.assertEquals(1, storeRepository.deleteByName("igor"));
    }

    @Transactional
    @Test
    void deleteAllTest() {
        storeRepository.save(new Store("igor", 23));
        storeRepository.save(new Store("vas", 30));
        storeRepository.deleteAll();

        Assertions.assertEquals(0, storeRepository.findAll().size());
    }

}
