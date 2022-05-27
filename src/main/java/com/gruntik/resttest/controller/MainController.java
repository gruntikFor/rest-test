package com.gruntik.resttest.controller;

import com.gruntik.resttest.entity.Store;
import com.gruntik.resttest.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
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
