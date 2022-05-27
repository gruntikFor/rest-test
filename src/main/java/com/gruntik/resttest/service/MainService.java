package com.gruntik.resttest.service;

import com.gruntik.resttest.entity.Store;
import com.gruntik.resttest.status.ResponseStatus;
import com.gruntik.resttest.validator.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class MainService {

    CustomValidator customValidator;
    StoreServiceImpl storeService;

    @Autowired
    public MainService(CustomValidator customValidator, StoreServiceImpl storeService) {
        this.customValidator = customValidator;
        this.storeService = storeService;
    }

    public Map<Object, Object> save(Store store) {
        Map<Object, Object> response = customValidator.validAdd(store);

        if (response.get("code").equals(ResponseStatus.OK.getValue())) {
            storeService.save(store);
        }
        return response;
    }

    public Map<Object, Object> remove(Map<String, String> data) {
        Map<Object, Object> response = customValidator.validRemove(data);

        if (response.get("code").equals(ResponseStatus.OK.getValue())) {
            int result = storeService.deleteByName(data.get("name"));
            if (result < 1) {
                response = CustomValidator.combineErrors(ResponseStatus.DELETE_ERROR);
            }
        }
        return response;
    }

    public Map<Object, Object> sum(Map<String, String> data) {
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
