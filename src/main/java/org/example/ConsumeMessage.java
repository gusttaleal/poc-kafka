package org.example;

import org.example.infrastructure.messenger.ConsumerStringExample;

public class ConsumeMessage {
    private static final ConsumerStringExample consumer = new ConsumerStringExample();

    public static void main(String[] args) {
        consumer.execute();
    }
}
