package org.example.core.utils;

import org.example.core.domain.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertiesMapperTest {
    @Test
    @DisplayName("should validate mapper")
    void validateMapper() {
        Map<String, String> expected = new HashMap<>();
        expected.put("fullName", "fullName");
        expected.put("birthDate", "birthDate");
        expected.put("identificationDocument", "identificationDocument");

        final String[] properties = Customer
                .builder()
                .fullName("fullName")
                .birthDate("birthDate")
                .identificationDocument("identificationDocument")
                .build()
                .toString()
                .replace("Customer(", "")
                .replace(")", "")
                .split(", ");

        var actual = PropertiesMapper.execute(properties);

        assertEquals(expected, actual);
    }
}