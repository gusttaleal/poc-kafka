package org.example;

import org.example.infrastructure.messenger.ConsumerStringExample;
import org.example.infrastructure.messenger.ProducerStringExample;

public class Main {
    private static final ProducerStringExample producer = new ProducerStringExample();
    private static final ConsumerStringExample consumer = new ConsumerStringExample();

    public static void main(String[] args) {
        producer.execute();
        consumer.execute();
    }
}
