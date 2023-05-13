package org.example.core.utils;

import java.util.HashMap;
import java.util.Map;

public class Mapper {

    public static Map<String, String> keyAndValue(final String[] keysAndValues) {
        Map<String, String> map = new HashMap<>();
        for (String keyAndValue : keysAndValues) {
            String key = keyAndValue.split("=")[0];
            String value = keyAndValue.split("=")[1];
            map.put(key, value);
        }
        return map;
    }
}
