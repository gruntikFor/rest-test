package com.gruntik.resttest.controller;

import com.gruntik.resttest.entity.Store;
import com.gruntik.resttest.service.MainService;
import com.gruntik.resttest.service.StoreService;
import com.gruntik.resttest.status.ResponseStatus;
import com.gruntik.resttest.validator.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    CustomValidator customValidator;
    StoreService storeService;
    MainService mainService;

    @Autowired
    public MainController(CustomValidator customValidator, StoreService storeService, MainService mainService) {
        this.customValidator = customValidator;
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
