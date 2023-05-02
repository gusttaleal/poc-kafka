package org.example.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerTest {

    @Test
    @DisplayName("should match all attributes")
    void matchAttributes() {
        List<String> expected = Arrays.asList(
                "fullName",
                "birthDate",
                "identificationDocument"
        );
        Field[] attributes = Customer.class.getDeclaredFields();

        List<String> actual = Arrays.stream(attributes).map(Field::getName).toList();

        assertTrue(actual.containsAll(expected));
    }
}