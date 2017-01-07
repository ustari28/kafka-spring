package com.alan.example.kafka.config;

import com.alan.example.kafka.KafkaConsumerLister;
import com.alan.example.kafka.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Executors;

/**
 * @author Alan Dávila<br>
 *         29 dic. 2016 21:14
 */
@Configuration
public class AppConfig {

    @Value("${kafka.host}")
    private String host;

    /**
     * Exposes Producer.
     * @return New Kafka producer.
     */
    @Bean
    public Producer producer() {
        final Properties props = new Properties();
        //Assign localhost id
        props.put("bootstrap.servers", host);
        //Set acknowledgements for producer requests.
        props.put("acks", "all");
        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);
        //Specify buffer size in config
        props.put("batch.size", 16384);
        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);
        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "com.alan.example.kafka.config.MessageSerializer");
        return new KafkaProducer<String, Message>(props);
    }

    /**
     * Exposes ObjectMapper.
     * @return New ObjectMapper.
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    /**
     * Exposes a consumer.
     * @return New Consumer.
     */
    @Bean
    public Consumer consumer() {
        final Properties props = new Properties();
        props.put("bootstrap.servers", host);
        //Change group.id for different consumers.
        props.put("group.id", "consumer-tutorial-3");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "com.alan.example.kafka.config.MessageDeserializer");
        props.put("client.id", "consumer-2");
        final Consumer<String, Message> consumer = new KafkaConsumer<String, Message>(props);
        consumer.subscribe(Arrays.asList("test1"));
        KafkaConsumerLister kafkaConsumerLister = new KafkaConsumerLister(consumer);
        Executors.newCachedThreadPool().execute(kafkaConsumerLister);
        return consumer;
    }


}
