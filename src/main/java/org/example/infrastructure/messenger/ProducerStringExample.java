package org.example.infrastructure.messenger;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.infrastructure.configuration.ProducerStringProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class ProducerStringExample {
    private final Logger LOG = LoggerFactory.getLogger(ProducerStringExample.class);

    private final ProducerRecord<String, String> RECORD = new ProducerRecord<>(
            "TOPIC",
            "KEY",
            String.valueOf(Math.random() * 1000 + 1).split("\\.")[0]
    );

    private final Callback CALLBACK = (data, ex) -> {
        if (ex != null)
            throw new RuntimeException("Error to produce message.");
        LOG.info(" ----- Producer Example Output ----- ");
        LOG.info("topic: " + data.topic()
                + ", partitions: " + data.partition()
                + ", offset: " + data.offset()
                + ", timestamp: " + new Date(data.timestamp())
                + ", key size: " + data.serializedKeySize()
                + ", value size: " + data.serializedValueSize()
        );
    };

    public void execute() {
        try (var producer = new KafkaProducer<String, String>(
                ProducerStringProperties.setup()
        )) {
            producer.send(RECORD, CALLBACK).get();
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        } finally {
            LOG.info(" ----- Produce Ended ----- ");
        }
    }
}
