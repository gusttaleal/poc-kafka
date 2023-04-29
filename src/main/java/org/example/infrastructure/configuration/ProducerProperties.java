package org.example.infrastructure.configuration;

import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

public class ProducerProperties {

    public static Properties setup(
            final String keySerializer,
            final String valueSerializer
    ) {
        var properties = new Properties();
        properties.setProperty(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "127.0.0.1:9092"
        );
        properties.setProperty(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                keySerializer
        );
        properties.setProperty(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                valueSerializer
        );
        return properties;
    }
}
