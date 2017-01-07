package com.alan.example.kafka.controller;

import com.alan.example.kafka.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.requests.ProduceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author Alan Dávila<br>
 *         e-mail ustargab@gmail.com<br>
 *         31 dic. 2016 00:46
 */
@RestController
@RequestMapping("/kafka/producer/v1")
@Slf4j
public class ProducesController {

    @Autowired
    private Producer producer;

    @RequestMapping(value = "/publish/{message}", method = RequestMethod.GET)
    public ResponseEntity<?> publishMessage(@PathVariable("message") final String message, @RequestParam("partition")
    final Integer
            partition) {
        final String k = "TEST_TOPIC";
        final Message mMessage = new Message();
        mMessage.setMessage(message);
        mMessage.setCreatedDate(LocalDateTime.now());
        log.info(mMessage.toString());
        ProducerRecord<String, Message> record = new ProducerRecord<String, Message>("test1", partition, k, mMessage);
        log.info(record.value().toString());
        producer.send(record);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
