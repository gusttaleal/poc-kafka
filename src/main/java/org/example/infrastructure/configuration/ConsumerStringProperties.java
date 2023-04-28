package org.example.infrastructure.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.infrastructure.messenger.ConsumerStringExample;

import java.util.Properties;

public class ConsumerStringProperties {
    public static Properties setup() {
        var properties = new Properties();
        properties.setProperty(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "127.0.0.1:9092"
        );
        properties.setProperty(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName()
        );
        properties.setProperty(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName()
        );
        properties.setProperty(
                ConsumerConfig.GROUP_ID_CONFIG,
                ConsumerStringExample.class.getSimpleName()
        );
        return properties;
    }
}
