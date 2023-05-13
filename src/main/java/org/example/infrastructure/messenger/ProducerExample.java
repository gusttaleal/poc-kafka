package org.example.infrastructure.messenger;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.infrastructure.configuration.ProducerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class ProducerExample {
    private final Logger LOG = LoggerFactory.getLogger(ProducerExample.class);

    private final Callback CALLBACK = (data, ex) -> {
        if (ex != null)
            throw new RuntimeException("Error to produce message.");
        LOG.info("\n\n ----- Producer Example Output ----- ");
        LOG.info("topic: " + data.topic()
                + ", partitions: " + data.partition()
                + ", offset: " + data.offset()
                + ", timestamp: " + new Date(data.timestamp())
                + ", key size: " + data.serializedKeySize()
                + ", value size: " + data.serializedValueSize()
                + "\n"
        );
    };

    private ProducerRecord<String, String> record(
            final String topic,
            final String key,
            final String value
    ) {
        return new ProducerRecord<>(topic, key, value);
    }

    public void execute(
            final String topic,
            final String key,
            final String value
    ) {
        try (var producer = new KafkaProducer<String, String>(
                ProducerProperties.setup(
                        StringSerializer.class.getName(),
                        StringSerializer.class.getName()
                )
        )) {
            producer.send(record(topic, key, value), CALLBACK).get();

        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        } finally {
            LOG.info(" ----- Produce Ended ----- ");
        }
    }
}
