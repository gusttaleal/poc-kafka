package org.example.core.utils;

import java.util.HashMap;
import java.util.Map;

public class PropertiesMapper {

    public static Map<String, String> execute(final String[] properties) {
        Map<String, String> map = new HashMap<>();
        for (String property : properties) {
            String[] keyValue = property.split("=");
            String key = keyValue[0];
            String value = keyValue[1];
            map.put(key, value);
        }
        return map;
    }
}
