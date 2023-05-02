package org.example.core.domain;

import lombok.Builder;
import lombok.Data;
import org.example.core.utils.PropertiesMapper;

import java.util.Map;

@Data
@Builder
public class Customer {
    private String fullName;
    private String birthDate;
    private String identificationDocument;

    public static Customer stringToObject(final String customerStr) {
        final String[] properties = customerStr
                .replace("Customer(", "")
                .replace(")", "")
                .split(", ");
        final Map<String, String> map = PropertiesMapper.execute(properties);

        return Customer.builder()
                .fullName(map.get("fullName"))
                .birthDate(map.get("birthDate"))
                .identificationDocument(map.get("identificationDocument"))
                .build();
    }
}
