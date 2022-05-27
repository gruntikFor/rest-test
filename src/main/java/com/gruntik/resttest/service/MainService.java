package com.gruntik.resttest.service;

import com.gruntik.resttest.entity.Store;

import java.util.Map;

public interface MainService {

    Map<Object, Object> save(Store store);

    Map<Object, Object> remove(Map<String, String> data);

    Map<Object, Object> sum(Map<String, String> data);
}
