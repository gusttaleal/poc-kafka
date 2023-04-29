package org.example;

import org.example.infrastructure.messenger.ProducerExample;

import java.util.UUID;

public class ProduceMessage {
    private static final ProducerExample producer = new ProducerExample();

    public static void main(String[] args) {
        producer.execute(
                "TOPIC",
                UUID.randomUUID().toString(),
                String.valueOf(Math.random() * 1000 + 1).split("\\.")[0]
        );
    }
}
