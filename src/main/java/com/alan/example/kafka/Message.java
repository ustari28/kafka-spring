package com.alan.example.kafka;

import com.alan.example.kafka.config.LocalDateTimeDeserializer;
import com.alan.example.kafka.config.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Alan Dávila<br>
 *         29 dic. 2016 21:17
 */
@Getter
@Setter
@ToString
public class Message implements Serializable{

    private String message;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdDate;
}
