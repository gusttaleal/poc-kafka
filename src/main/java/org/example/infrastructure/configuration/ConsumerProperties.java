package org.example.infrastructure.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

public class ConsumerProperties {
    public static Properties setup(
            final String keySerializer,
            final String valueSerializer,
            final String groupId
    ) {
        var properties = new Properties();
        properties.setProperty(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "127.0.0.1:9092"
        );
        properties.setProperty(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                keySerializer
        );
        properties.setProperty(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                valueSerializer
        );
        properties.setProperty(
                ConsumerConfig.GROUP_ID_CONFIG,
                groupId
        );
        properties.setProperty(
                ConsumerConfig.MAX_POLL_RECORDS_CONFIG,
                "1"
        );
        return properties;
    }
}
