package org.example;

import org.example.infrastructure.messenger.ProducerStringExample;

public class ProduceMessage {
    private static final ProducerStringExample producer = new ProducerStringExample();

    public static void main(String[] args) {
        producer.execute();
    }
}
