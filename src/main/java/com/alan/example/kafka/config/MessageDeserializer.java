package com.alan.example.kafka.config;

import com.alan.example.kafka.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import javaslang.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

/**
 * @author Alan Dávila<br>
 *         30 dic. 2016 10:36
 */
@Slf4j
public class MessageDeserializer implements Deserializer<Message> {
    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String propertyName = isKey ? "key.deserializer.encoding" : "value.deserializer.encoding";
        Object encodingValue = configs.get(propertyName);
        if (encodingValue == null)
            encodingValue = configs.get("deserializer.encoding");
        if (encodingValue != null && encodingValue instanceof String)
            encoding = (String) encodingValue;
    }

    @Override
    public Message deserialize(final String topic, final byte[] data) {
        try {
            log.info(new String(data, "UTF-8"));
            return new ObjectMapper().readValue(data, Message.class);
        } catch (IOException e) {
            log.error("Can't read json");
            return null;
        }
    }

    @Override
    public void close() {

    }
}
