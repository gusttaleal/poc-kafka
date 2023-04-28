package org.example.infrastructure.messenger;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.example.infrastructure.configuration.ConsumerStringProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;

public class ConsumerStringExample {
    private final Logger LOG = LoggerFactory.getLogger(ConsumerStringExample.class);

    private void PRESENT_RECORDS(ConsumerRecord<String, String> data) {
        LOG.info(" ----- Consumer Example Output ----- ");
        LOG.info("topic: " + data.topic()
                + ", partitions: " + data.partition()
                + ", offset: " + data.offset()
                + ", timestamp: " + new Date(data.timestamp())
                + ", key: " + data.key()
                + ", value: " + data.value()
                + ", headers: " + data.headers()
                + ", leaderEpoch: " + data.leaderEpoch()
        );
    }

    public void execute() {
        try (var consumer = new KafkaConsumer<String, String>(
                ConsumerStringProperties.setup()
        )) {
            consumer.subscribe(Collections.singletonList("TOPIC"));
            for (int i = 0; i < 100; i++) {
                var records = consumer.poll(Duration.ofMillis(1000));
                if (!records.isEmpty())
                    records.forEach(this::PRESENT_RECORDS);
                LOG.warn("No message found");
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        } finally {
            LOG.info(" ----- Consumer Ended ----- ");
        }
    }
}
