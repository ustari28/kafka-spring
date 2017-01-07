package com.alan.example.kafka.config;

import com.alan.example.kafka.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;
import java.util.Optional;

/**
 * @author Alan Dávila<br>
 *         29 dic. 2016 21:25
 */
@Slf4j
public class MessageSerializer implements Serializer<Message> {

    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String propertyName = isKey ? "key.serializer.encoding" : "value.serializer.encoding";
        Object encodingValue = configs.get(propertyName);
        if (encodingValue == null)
            encodingValue = configs.get("serializer.encoding");
        if (encodingValue != null && encodingValue instanceof String)
            encoding = (String) encodingValue;
    }

    @Override
    public byte[] serialize(final String topic, final Message data) {
        try {
            return new ObjectMapper().writeValueAsBytes(Optional.of(data).orElse(null));
        } catch (JsonProcessingException e) {
            log.error("Can't write json");
            return null;
        }
    }

    @Override
    public void close() {
        //nothing to do
    }
}
