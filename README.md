#Producer and Consumer
______________________
This example was created with Spring-boot, apache-kafka.
We use docker from kafka image:
```
docker run -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=localhost --env ADVERTISED_PORT=9092 --name local-kafka spotify/kafka
```

To enter into kafka instance:
```
docker exec -i -t NAME_KAFKA_MACHINE /bin/bash
```

It's necessary to create a topic before to send a new message to kafka:
```
kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 3 --topic test1
```

To custom your consumer you have to change these properties:
```
props.put("bootstrap.servers", KAFKA_HOST);
props.put("group.id", "CONSUMER_GROUP");
props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); //OR CUSTOM DESERIALIZER
```

To custom your producer you have to change these properties:
```
props.put("bootstrap.servers", KAFKA_HOST);
props.put("acks", "all");
props.put("retries", 0);
props.put("batch.size", 16384);
props.put("linger.ms", 1);
props.put("buffer.memory", 33554432);
props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); //OR CUSTOM SERIALIZER
```

To send a message from Http request:
```
http://localhost:8081/kafka/producer/v1/publish/HOLAMUNDO?partition=1
```
