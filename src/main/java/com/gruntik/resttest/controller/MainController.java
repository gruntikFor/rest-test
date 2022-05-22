package com.gruntik.resttest.controller;

import com.gruntik.resttest.ErrorStatus;
import com.gruntik.resttest.ErrorStatusCombiner;
import com.gruntik.resttest.dao.StoreRepository;
import com.gruntik.resttest.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    StoreRepository storeRepository;

    @Autowired
    public void setStoreRepository(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    Map<Object, Object> messages = new LinkedHashMap<>();

    @GetMapping("/")
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @PostMapping("/add")
    public Map<Object, Object> add(@RequestBody Store store) {

        System.out.println(storeRepository.findAll());

        if (store.getName() == null) {
            return ErrorStatusCombiner.combineErrors(ErrorStatus.NO_NAME);
        }

        if (store.getValue() == null) {
            return ErrorStatusCombiner.combineErrors(ErrorStatus.NO_VALUE);
        }

        if (storeRepository.existsByName(store.getName())) {
            return ErrorStatusCombiner.combineErrors(ErrorStatus.ALREADY_EXISTS);
        }
        storeRepository.save(store);

        messages.put("code", ErrorStatus.OK.getValue());
        messages.put("description", ErrorStatus.OK.getDescription());
        return messages;
    }

    @Transactional
    @PostMapping("/remove")
    public Map<Object, Object> remove(@RequestBody Map<String, String> data) {

        if (data.size() == 0) {
            return ErrorStatusCombiner.combineErrors(ErrorStatus.NO_DATA);
        }

        if (storeRepository.deleteByName(data.get("name")) == 0) {
            return ErrorStatusCombiner.combineErrors(ErrorStatus.NOTHING_TO_DELETE);
        }

        messages.put("code", ErrorStatus.OK.getValue());
        messages.put("description", ErrorStatus.OK.getDescription());
        return messages;
    }

    @PostMapping("/sum")
    public Map<Object, Object> sum(@RequestBody Map<String, String> data) {
        messages.clear();

        int first;
        int second;

        if (data.size() == 0) {
            return ErrorStatusCombiner.combineErrors(ErrorStatus.NO_DATA);
        }

        if (!data.containsKey("first")) {
            return ErrorStatusCombiner.combineErrors(ErrorStatus.NO_FIRST_NUMBER);
        }

        if (!data.containsKey("second")) {
            return ErrorStatusCombiner.combineErrors(ErrorStatus.NO_SECOND_NUMBER);
        }

        try {
            first = Integer.parseInt(data.get("first"));
        } catch (Exception e) {
            return ErrorStatusCombiner.combineErrors(ErrorStatus.NOT_NUMBER_FIRST);
        }

        try {
            second = Integer.parseInt(data.get("second"));
        } catch (Exception e) {
            return ErrorStatusCombiner.combineErrors(ErrorStatus.NOT_NUMBER_SECOND);
        }

        Integer sum = first + second;

        messages.put("sum", sum);
        messages.put("code", ErrorStatus.OK.getValue());
        messages.put("description", ErrorStatus.OK.getDescription());
        return messages;
    }
}
