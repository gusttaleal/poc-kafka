package org.example.core.utils;

import java.lang.reflect.Field;
import java.util.Map;

public class StringToObject {
    public static <T> T execute(String stringObject) throws Exception {
        final String className = stringObject.split("\\(")[0];
        final String[] keysAndValues = stringObject
                .replace(className, "")
                .replace("(", "")
                .replace(")", "")
                .split(", ");

        final Map<String, String> map = Mapper.keyAndValue(keysAndValues);

        final String domainPackage = "org.example.core.domain.";
        var clazz = Class.forName(domainPackage + className);
        var obj = clazz.getDeclaredConstructor().newInstance();

        for (Field field : clazz.getDeclaredFields()) {
            String fieldValue = map.get(field.getName());
            if (fieldValue != null) {
                field.setAccessible(true);
                if (field.getType() == int.class) {
                    field.setInt(obj, Integer.parseInt(fieldValue));
                } else if (field.getType() == String.class) {
                    field.set(obj, fieldValue);
                }
            }
        }
        return (T) obj;
    }
}
