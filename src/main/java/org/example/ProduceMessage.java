package org.example;

import org.example.core.domain.Customer;
import org.example.core.domain.Product;
import org.example.infrastructure.messenger.ProducerExample;

import java.util.UUID;

public class ProduceMessage {
    private static final ProducerExample producer = new ProducerExample();

    public static void main(String[] args) {
        producer.execute(
                "TOPIC",
                UUID.randomUUID().toString(),
                Customer.builder()
                        .birthDate("01/01/2000")
                        .fullName("Gustavo Leal")
                        .identificationDocument("123.456.789-00")
                        .build()
                        .toString()
        );
        producer.execute(
                "TOPIC",
                UUID.randomUUID().toString(),
                Product.builder()
                        .id(UUID.randomUUID().toString())
                        .productName("Rice")
                        .expirationDate("01/01/3000")
                        .build()
                        .toString()
        );
    }
}
