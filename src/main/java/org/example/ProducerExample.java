package org.example;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Date;
import java.util.Properties;

public class ProducerExample {

    private static final ProducerRecord<String, String> RECORD = new ProducerRecord<>(
            "TOPIC",
            "KEY",
            String.valueOf(Math.random() * 1000 + 1).split("\\.")[0]
    );

    private static final Callback CALLBACK = (data, ex) -> {
        if (ex != null) {
            ex.getMessage();
            return;
        }
        System.out.println("topic: " + data.topic()
                + ", partitions: " + data.partition()
                + ", offset: " + data.offset()
                + ", timestamp: " + new Date(data.timestamp())
                + ", key: " + data.serializedKeySize()
                + ", value: " + data.serializedValueSize()
        );
    };

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092"
        );
        properties.setProperty(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()
        );
        properties.setProperty(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()
        );
        return properties;
    }

    public static void main(String[] args) {
        try (var producer = new KafkaProducer<String, String>(properties())) {
            producer.send(RECORD, CALLBACK).get();
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
        }
    }
}
