package com.alan.example.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

/**
 * @author Alan Dávila<br>
 *         31 dic. 2016 00:28
 */
@Slf4j
public class KafkaConsumerLister implements Runnable {

    private final static Long CONSUMER_IDLE = 60000L;
    private Consumer consumer;

    public KafkaConsumerLister(final Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        while (true) {
            ConsumerRecords<String, Message> records = consumer.poll(CONSUMER_IDLE);
            for (ConsumerRecord<String, Message> record : records) {
                log.info(record.toString());
            }
            //Optional.of(records).orElse(null).forEach(r -> log.info(r.value().toString()));
        }
    }


}
