package org.example.infrastructure.messenger;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.core.domain.Customer;
import org.example.infrastructure.configuration.ConsumerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;

public class ConsumerExample {
    private final Logger LOG = LoggerFactory.getLogger(ConsumerExample.class);

    private void presentRecords(ConsumerRecord<String, String> data) {
        LOG.info("\n\n ----- Consumer Example Output ----- ");
        LOG.info("topic: " + data.topic()
                + ", partitions: " + data.partition()
                + ", offset: " + data.offset()
                + ", timestamp: " + new Date(data.timestamp())
                + ", key: " + data.key()
                + ", value: " + data.value()
                + ", headers: " + data.headers()
                + ", leaderEpoch: " + data.leaderEpoch()
        );
        LOG.info("\nCustomer: " + Customer.stringToObject(data.value()).getIdentificationDocument());
    }

    public void execute(final String topic) {
        try (var consumer = new KafkaConsumer<String, String>(
                ConsumerProperties.setup(
                        StringDeserializer.class.getName(),
                        StringDeserializer.class.getName(),
                        ConsumerExample.class.getSimpleName()
                )
        )) {
            consumer.subscribe(Collections.singletonList(topic));
            for (int i = 0; i < 30; i++) {
                var records = consumer.poll(Duration.ofMillis(1000));
                if (!records.isEmpty())
                    records.forEach(this::presentRecords);
            }

        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        } finally {
            LOG.info(" ----- Consumer Ended ----- ");
        }
    }
}
