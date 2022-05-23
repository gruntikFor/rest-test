package com.gruntik.resttest.validator;

import com.gruntik.resttest.status.ResponseStatus;
import com.gruntik.resttest.dao.StoreRepository;
import com.gruntik.resttest.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CustomValidator {

    StoreRepository storeRepository;

    @Autowired
    public void setStoreRepository(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Map<Object, Object> validAdd(Store store) {
        if (store.getName() == null) {
            return combineErrors(ResponseStatus.NO_NAME);
        }

        if (store.getValue() == null) {
            return combineErrors(ResponseStatus.NO_VALUE);
        }

        if (storeRepository.existsByName(store.getName())) {
            return combineErrors(ResponseStatus.ALREADY_EXISTS);
        }

        return combineErrors(ResponseStatus.OK);
    }

    public Map<Object, Object> validRemove(Map<String, String> data) {
        if (data.size() == 0) {
            return combineErrors(ResponseStatus.NO_DATA);
        }

        if (!storeRepository.existsByName(data.get("name"))) {
            return combineErrors(ResponseStatus.NOTHING_TO_DELETE);
        }

        return combineErrors(ResponseStatus.OK);
    }

    public LinkedHashMap<Object, Object> validSum(Map<String, String> data) {
        if (data.size() == 0) {
            return combineErrors(ResponseStatus.NO_DATA);
        }

        if (!data.containsKey("first")) {
            return combineErrors(ResponseStatus.NO_FIRST_NUMBER);
        }

        if (!data.containsKey("second")) {
            return combineErrors(ResponseStatus.NO_SECOND_NUMBER);
        }

        try {
            Integer.parseInt(data.get("first"));
        } catch (Exception e) {
            return combineErrors(ResponseStatus.NOT_NUMBER_FIRST);
        }

        try {
            Integer.parseInt(data.get("second"));
        } catch (Exception e) {
            return combineErrors(ResponseStatus.NOT_NUMBER_SECOND);
        }

        return combineErrors(ResponseStatus.OK);
    }


    public static LinkedHashMap<Object, Object> combineErrors(ResponseStatus responseStatus) {
        LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
        response.put("code", responseStatus.getValue());
        response.put("description", responseStatus.getDescription());
        return response;
    }
}
