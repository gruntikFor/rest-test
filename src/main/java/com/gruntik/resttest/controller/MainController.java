package com.gruntik.resttest.controller;

import com.gruntik.resttest.ErrorStatus;
import com.gruntik.resttest.dao.StoreRepository;
import com.gruntik.resttest.entity.Store;
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
        System.out.println(store);

        if (store.getName() == null) {
            messages.put("code", ErrorStatus.NO_NAME.getValue());
            messages.put("description", ErrorStatus.NO_NAME.getDescription());
            return messages;
        }

        if (store.getValue() == null) {
            messages.put("code", ErrorStatus.NO_VALUE.getValue());
            messages.put("description", ErrorStatus.NO_VALUE.getDescription());
            return messages;
        }

        if (storeRepository.existsByName(store.getName())) {
            messages.put("code", ErrorStatus.ALREADY_EXISTS.getValue());
            messages.put("description", ErrorStatus.ALREADY_EXISTS.getDescription());
            return messages;
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
            messages.put("code", ErrorStatus.NO_DATA.getValue());
            messages.put("description", ErrorStatus.NO_DATA.getDescription());
            return messages;
        }

        if (storeRepository.deleteByName(data.get("name")) == 0) {
            messages.put("code", ErrorStatus.NOTHING_TO_DELETE.getValue());
            messages.put("description", ErrorStatus.NOTHING_TO_DELETE.getDescription());
            return messages;
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
            messages.put("code", ErrorStatus.NO_DATA.getValue());
            messages.put("description", ErrorStatus.NO_DATA.getDescription());
            return messages;
        }

        if (!data.containsKey("first")) {
            messages.put("code", ErrorStatus.NO_FIRST_NUMBER.getValue());
            messages.put("description", ErrorStatus.NO_FIRST_NUMBER.getDescription());
            return messages;
        }

        if (!data.containsKey("second")) {
            messages.put("code", ErrorStatus.NO_SECOND_NUMBER.getValue());
            messages.put("description", ErrorStatus.NO_SECOND_NUMBER.getDescription());
            return messages;
        }

        try {
            first = Integer.parseInt(data.get("first"));
        } catch (Exception e) {
            messages.put("code", ErrorStatus.NOT_NUMBER_FIRST.getValue());
            messages.put("description", ErrorStatus.NOT_NUMBER_FIRST.getDescription());
            return messages;
        }

        try {
            second = Integer.parseInt(data.get("second"));
        } catch (Exception e) {
            messages.put("code", ErrorStatus.NOT_NUMBER_SECOND.getValue());
            messages.put("description", ErrorStatus.NOT_NUMBER_SECOND.getDescription());
            return messages;
        }

        Integer sum = first + second;

        messages.put("sum", sum);
        messages.put("code", ErrorStatus.OK.getValue());
        messages.put("description", ErrorStatus.OK.getDescription());
        return messages;
    }
}
