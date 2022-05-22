package com.gruntik.resttest.validator;

import com.gruntik.resttest.status.ErrorStatus;
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
            return combineErrors(ErrorStatus.NO_NAME);
        }

        if (store.getValue() == null) {
            return combineErrors(ErrorStatus.NO_VALUE);
        }

        if (storeRepository.existsByName(store.getName())) {
            return combineErrors(ErrorStatus.ALREADY_EXISTS);
        }

        return combineErrors(ErrorStatus.OK);
    }

    public Map<Object, Object> validRemove(Map<String, String> data) {
        if (data.size() == 0) {
            return combineErrors(ErrorStatus.NO_DATA);
        }

        if (!storeRepository.existsByName(data.get("name"))) {
            return combineErrors(ErrorStatus.NOTHING_TO_DELETE);
        }

        return combineErrors(ErrorStatus.OK);
    }

    public LinkedHashMap<Object, Object> validSum(Map<String, String> data) {
        if (data.size() == 0) {
            return combineErrors(ErrorStatus.NO_DATA);
        }

        if (!data.containsKey("first")) {
            return combineErrors(ErrorStatus.NO_FIRST_NUMBER);
        }

        if (!data.containsKey("second")) {
            combineErrors(ErrorStatus.NO_SECOND_NUMBER);
        }

        try {
            Integer.parseInt(data.get("first"));
        } catch (Exception e) {
            return combineErrors(ErrorStatus.NOT_NUMBER_FIRST);
        }

        try {
            Integer.parseInt(data.get("second"));
        } catch (Exception e) {
            return combineErrors(ErrorStatus.NOT_NUMBER_SECOND);
        }

        return combineErrors(ErrorStatus.OK);
    }


    public static LinkedHashMap<Object, Object> combineErrors(ErrorStatus errorStatus) {
        LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
        response.put("code", errorStatus.getValue());
        response.put("description", errorStatus.getDescription());
        return response;
    }
}
