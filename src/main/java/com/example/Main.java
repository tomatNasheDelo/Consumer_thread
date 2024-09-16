package com.example;


import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class Main {

    // private static final String TOPIC =  "kafka_thread";
    // private static String BOOTSTRAP_SERVERS = "localhost:9092";
    public static void main(String[] args){

        Thread consumerThread = new Thread(Main::consume);
        consumerThread.start();


    }

    private static void consume(){
        
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test-group");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "5000");
         props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

consumer.subscribe(Arrays.asList("kafka_thread"));
try {
    while (true) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
        for (ConsumerRecord<String, String> record : records) {
            System.out.println("Received message: (key: " + record.key() + ", value: " + record.value() + ") at offset " + record.offset());
        }
    }
} finally {
    consumer.close();
}
            
        }

}


