package com.alan.example.kafka.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author Alan Dávila<br>
 *         03 ene. 2017 23:01
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(final LocalDateTime value, final JsonGenerator gen, final SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeString(value.toString());
    }
}
