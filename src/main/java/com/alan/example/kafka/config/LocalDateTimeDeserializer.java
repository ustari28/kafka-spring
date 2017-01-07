package com.alan.example.kafka.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author Alan Dávila<br>
 *         03 ene. 2017 23:06
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return LocalDateTime.parse(p.getText());
    }
}
