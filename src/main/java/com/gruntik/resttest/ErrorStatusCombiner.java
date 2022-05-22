package com.gruntik.resttest;

import java.util.Map;

public class ErrorStatusCombiner {

    public static Map<Object, Object> combineErrors(ErrorStatus errorStatus) {
        return Map.of("code", errorStatus.getValue(),
                "description", errorStatus.getDescription());
    }
}
