package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;

public class ConsumerExample {

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092"
        );
        properties.setProperty(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()
        );
        properties.setProperty(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()
        );
        properties.setProperty(
                ConsumerConfig.GROUP_ID_CONFIG, ConsumerExample.class.getSimpleName()
        );
        return properties;
    }

    public static void main(String[] args) {
        try (var consumer = new KafkaConsumer<String, String>(properties())) {
            consumer.subscribe(Collections.singletonList("TOPIC"));
            while (true) {
                var records = consumer.poll(Duration.ofMillis(10000));
                if (records.isEmpty()) return;
                records.forEach(data ->
                        System.out.println("topic: " + data.topic()
                                + ", partitions: " + data.partition()
                                + ", offset: " + data.offset()
                                + ", timestamp: " + new Date(data.timestamp())
                                + ", key: " + data.key()
                                + ", value: " + data.value()
                                + ", headers: " + data.headers()
                                + ", leaderEpoch: " + data.leaderEpoch()
                        )
                );
            }
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
        }
    }
}
