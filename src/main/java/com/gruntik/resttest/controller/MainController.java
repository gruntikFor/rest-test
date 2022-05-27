package com.gruntik.resttest.controller;

import com.gruntik.resttest.entity.Store;
import com.gruntik.resttest.service.MainService;
import com.gruntik.resttest.service.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    private final StoreServiceImpl storeService;
    private final MainService mainService;

    @Autowired
    public MainController(StoreServiceImpl storeService, MainService mainService) {
        this.storeService = storeService;
        this.mainService = mainService;
    }

    @GetMapping("/")
    public List<Store> findAll() {
        return storeService.findAll();
    }

    @PostMapping("/add")
    public Map<Object, Object> add(@RequestBody Store store) {
        return mainService.save(store);
    }

    @Transactional
    @PostMapping("/remove")
    public Map<Object, Object> remove(@RequestBody Map<String, String> data) {
        return mainService.remove(data);
    }

    @PostMapping("/sum")
    public Map<Object, Object> sum(@RequestBody Map<String, String> data) {
        return mainService.sum(data);
    }

}
