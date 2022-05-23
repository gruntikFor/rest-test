package com.gruntik.resttest.controller;

import com.gruntik.resttest.dao.StoreRepository;
import com.gruntik.resttest.entity.Store;
import com.gruntik.resttest.status.ResponseStatus;
import com.gruntik.resttest.validator.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class MainController {

    StoreRepository storeRepository;
    CustomValidator customValidator;

    @Autowired
    public void setStoreRepository(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Autowired
    public void setCustomValidator(CustomValidator customValidator) {
        this.customValidator = customValidator;
    }

    @PostMapping("/add")
    public Map<Object, Object> add(@RequestBody Store store) {
        Map<Object, Object> response = customValidator.validAdd(store);

        if (response.get("code").equals(ResponseStatus.OK)) {
            storeRepository.save(store);
        }
        return response;
    }

    @Transactional
    @PostMapping("/remove")
    public Map<Object, Object> remove(@RequestBody Map<String, String> data) {
        Map<Object, Object> response = customValidator.validRemove(data);

        if (response.get("code").equals(ResponseStatus.OK)) {
            storeRepository.deleteByName(data.get("name"));
        }
        return response;
    }

    @PostMapping("/sum")
    public Map<Object, Object> sum(@RequestBody Map<String, String> data) {
        Map<Object, Object> validData = customValidator.validSum(data);
        Map<Object, Object> response = new LinkedHashMap<>();
        Integer sum = null;

        if (validData.get("code").equals(ResponseStatus.OK.getValue())) {
            sum = Integer.parseInt(data.get("first")) + Integer.parseInt(data.get("second"));
        }

        if (sum != null) {
            response.put("sum", sum);
        }
        response.putAll(validData);
        return response;
    }
}
