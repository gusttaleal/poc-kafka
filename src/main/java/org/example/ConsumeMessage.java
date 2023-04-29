package org.example;

import org.example.infrastructure.messenger.ConsumerExample;

public class ConsumeMessage {
    private static final ConsumerExample consumer = new ConsumerExample();

    public static void main(String[] args) {
        consumer.execute("TOPIC");
    }
}
